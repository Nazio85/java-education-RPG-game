package pro.x_way.skils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pro.x_way.units.Unit;

public abstract class Skill {
    public static float ROTATION_DX = 25f;
    public static float ANIMATION_TIME = 1.0f;

    protected boolean active = false;
    protected Unit currentUnit;
    protected Unit targetUnit;
    protected float skillAnimationTime;

    public Skill(Unit currentUnit, Unit targetUnit) {
        active = true;
        this.currentUnit = currentUnit;
        this.targetUnit = targetUnit;
        skillAnimationTime = ANIMATION_TIME;
        action();
    }

    protected abstract void action ();
    public abstract void render(SpriteBatch batch);
    public  void update (float dt){
        if (skillAnimationTime <= 0) {
            active = false;
            currentUnit.setRotationDx(0);
            currentUnit.setRotationDy(0);
            targetUnit.setRotationDx(0);
            targetUnit.setRotationDy(0);
            currentUnit.setUnitColor(Color.WHITE);
            targetUnit.setUnitColor(Color.WHITE);
        }
        else skillAnimationTime -= dt;
    }

    public boolean isActive(){
        return active;
    }
}
