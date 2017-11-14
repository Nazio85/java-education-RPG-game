package pro.x_way.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import pro.x_way.*;
import pro.x_way.effect.*;


public abstract class Unit {
    public static final int FULL_STATUS = 90;
    public static final int HEIGHT_STATUS_BAR_ROW = 13;
    public static final int HEIGHT_STATUS_BAR = 26;

    public static final int TYPE_DAMAGE = -1;
    public static final int TYPE_HILL = 1;

    //Base
    protected String name;
    protected Texture unitView;
    protected int unitHeight = 120;
    protected int unitWeight = 80;
    protected Texture textureStatus;
    protected int hp;
    protected int maxHp;
    protected int mp;
    protected int maxMp;
    protected int level;
    protected boolean alive;
    protected boolean isEnemy;

    //Stat
    protected int strength;
    protected int defence;
    protected int dexterity;
    protected int endurance;
    protected int spellPower;
    protected int initiative;

    //Bonus
    protected List<Effect> effectList;

    //Rotation
    protected boolean flip;
    protected Vector2 position;
    protected int rotation = 0;
    protected Rectangle formUnit;

    //StatusBar
    protected SpriteBatch batch;
    protected Pixmap pixmap;

    //Action
    protected float rotationDx;
    protected float rotationDy;
    protected Color unitColor;

    public Unit(Vector2 position, Texture unitView, int strength, int defence, int endurance,
                int dexterity, int initiative, int spellPower, int mana,
                boolean isEnemy) {
        this.position = position;
        this.unitView = unitView;
        this.formUnit = new Rectangle(position.x, position.y, unitView.getWidth(), unitView.getHeight());
        this.alive = true;

        this.hp = endurance * 10;
        this.maxHp = endurance * 10;
        this.mp = mana * 15;
        this.maxMp = mana * 15;
        this.strength = strength;
        this.defence = defence;
        this.dexterity = dexterity;
        this.initiative = initiative;
        this.spellPower = spellPower;
        this.flip = isEnemy; //повернуть как врага
        this.isEnemy = isEnemy;

        effectList = new ArrayList<Effect>();
        textureStatus = new Texture(getStatusBarTexture());

        rotationDx = 0;
        rotationDy = 0;
        unitColor = Color.WHITE;

    }

    public boolean isEnemy() {
        return isEnemy;
    }


    public void render(SpriteBatch batch) {
        this.batch = batch;


        batch.setColor(unitColor);
        if (flip) rotationDx *= -1;
        batch.draw(unitView,
                position.x + rotationDx, position.y + rotationDy,                   // позиция
                unitView.getWidth() / 2, unitView.getHeight() / 2,  // средняя точка
                unitWeight, unitHeight,                        // размеры
                1, 1,                                              //scale - масштаб
                rotation,                                                      // Поворот в градусах
                0, 0,                                                 // Начальная точка для вырезания
                unitView.getWidth(), unitView.getHeight(),                        // Размер вырезания
                flip, false);                                              //отразить
        batch.setColor(Color.WHITE);

        statusBar();
    }

    protected void statusBar() {
        if (alive) {
            batch.setColor(0, 0, 0, 1);
            batch.draw(textureStatus, position.x, position.y + unitHeight - HEIGHT_STATUS_BAR_ROW,
                    FULL_STATUS, HEIGHT_STATUS_BAR_ROW * 2);
            batch.setColor(1, 0, 0, 1);
            batch.draw(textureStatus, position.x, position.y + unitHeight,
                    Calculator.calculateStatusBar(maxHp, hp), HEIGHT_STATUS_BAR_ROW);
            batch.setColor(0, 0, 1, 1);
            batch.draw(textureStatus, position.x, position.y + unitHeight - HEIGHT_STATUS_BAR_ROW,
                    Calculator.calculateStatusBar(maxMp, mp), HEIGHT_STATUS_BAR_ROW);
            batch.setColor(1, 1, 1, 1);
            GameText.printStatusBar(this, batch);
        }
    }

    private Pixmap getStatusBarTexture() {
        pixmap = new Pixmap(FULL_STATUS, HEIGHT_STATUS_BAR, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        pixmap.setColor(1, 1, 1, 1);
        pixmap.fillRectangle(0, 0, Calculator.calculateStatusBar(maxHp, hp), HEIGHT_STATUS_BAR);
        return pixmap;
    }

    public boolean isDead (){
        if (hp <= 0){
            rotation = 275; //dead
            alive = false;
            return true;
        }
        return false;
    }

    public void update(float dt) {

    }

    public void setHp(int damageOrHill, int type) {
        if (type == TYPE_DAMAGE) {
            this.hp -= damageOrHill;
            GameText.damage(this, damageOrHill);

        }

        if (type == TYPE_HILL) {
            int tmpHP = hp;
            hp += damageOrHill;
            if (hp > maxHp) hp = maxHp;
            GameText.hill(this, hp - tmpHP);
        }
    }

    public void dodged() {
        GameText.miss(this);
    }

    public void updateBonus() {
        for (int i = 0; i < effectList.size(); i++) {
            if (effectList.get(i).isEnd()) {
                effectList.get(i).end();
                effectList.remove(i);
            } else {
                effectList.get(i).tick();
            }
        }
    }

    public Rectangle getFormUnit() {
        return formUnit;
    }


    public void unitDefence() {
        DefenceEffect effect = new DefenceEffect(this);
        effect.start(DefenceEffect.TIME);
        effectList.add(effect);
    }


    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMp() {
        return mp;
    }

    public int getInitiative() {
        return initiative;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getUnitView() {
        return unitView;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getSpellPower() {
        return spellPower;
    }

    public String getName() {
        return name;
    }

    public int getDefence() {
        return defence;
    }

    public int getUnitHeight() {
        return unitHeight;
    }

    public int getUnitWeight() {
        return unitWeight;
    }

    public void setDefence(int defence) {
        this.defence += defence;
    }

    public void setRotationDx(float rotationDx) {
        this.rotationDx = rotationDx;
    }

    public void setRotationDy(float rotationDy) {
        this.rotationDy = rotationDy;
    }

    public void setUnitColor(Color unitColor) {
        this.unitColor = unitColor;
    }

    public void dispose() {
        pixmap.dispose();
        unitView.dispose();
        textureStatus.dispose();

    }
}
