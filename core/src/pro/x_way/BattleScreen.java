package pro.x_way;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pro.x_way.skils.FabricSkills;
import pro.x_way.units.Player;
import pro.x_way.units.Skeleton;
import pro.x_way.units.Unit;

public class BattleScreen implements Screen {
    public static final float TIME_BETWEEN_STEP = 1.5f;
    public static final String BTN_ATTACK = "btnAttack";
    private SpriteBatch batch;
    private BattleBackGround background;
    private List<Unit> startTeam;
    public static List<Unit> currentTeam;


    private Stage stage; // хранилище интерфейсов (слой экрана)
    private Skin skin; //Офрмление - как будут выглядеть кнопки

    public static Unit selectUnit;
    public static Unit currentUnit;

    private int step;
    private SpecialFX fx;
    private Texture currentUserArrow;
    private Texture selectUserArrow;
    private List<Button> buttons;
    private float timeBetweenStep;

    private InputHandler inputHandler;

    public BattleScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        Assets.getInstance().loadAssets(ScreenManager.ScreenType.BATTLE);
        inputHandler = new InputHandler();
        Gdx.input.setInputProcessor(inputHandler);
        createGUI();
        Gdx.input.setInputProcessor(stage);
        fx = new SpecialFX();
        background = new BattleBackGround();
        GameText.gameTextSetup();
        startTeam = new ArrayList<Unit>();
        FabricSkills.setup();

        startTeam.add(new Skeleton("Skeleton", new Vector2(400, 450), false)); // создаем плеера и передаем его в лист
        startTeam.add(new Player("Aleksandr", new Vector2(400, 300), false)); // создаем плеера и передаем его в лист
        startTeam.add(new Skeleton("Skeleton1", new Vector2(700, 450), true)); // создаем скелетона и передаем его в лист
        startTeam.add(new Skeleton("Skeleton2", new Vector2(700, 300), true)); // создаем скелетона и передаем его в лист
        startTeam.add(new Skeleton("Skeleton3", new Vector2(700, 150), true)); // создаем скелетона и передаем его в лист
        Collections.sort(startTeam, new SortedByInitiative());
        currentTeam = new ArrayList<Unit>(startTeam);
        currentUnit = currentTeam.get(step = 0);

        currentUserArrow = Assets.getInstance().getAssetManager().get("current_user_arrow.png");
        selectUserArrow = Assets.getInstance().getAssetManager().get("select_user_arrow.png");


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);

        GameText.render(batch);
        cursor();
        update(delta);
        fx.render(batch);

        for (int i = 0; i < startTeam.size(); i++) {
            startTeam.get(i).render(batch);
        }


        //Render skills
        for (int i = 0; i < FabricSkills.getAllSkills().size(); i++) {
            if (FabricSkills.getAllSkills().get(i).isActive()) {
                FabricSkills.getAllSkills().get(i).render(batch);
                FabricSkills.getAllSkills().get(i).update(delta);
            } else FabricSkills.getAllSkills().remove(i);
        }

        GameText.printFlyingText(batch);
        batch.end();
        stage.draw();
    }


    private void cursor() {
        for (int i = 0; i < currentTeam.size(); i++) {
            if (currentTeam.get(i) == currentUnit && currentTeam.get(i).isAlive()) {
                batch.draw(currentUserArrow, currentUnit.getPosition().x, currentUnit.getPosition().y - 50,
                        100, 100);
            }

            //Выбор персонажа
            selectUnit();
            if (selectUnit == null) {
                if (currentTeam.get(i).isEnemy() && !currentUnit.isEnemy()
                        || !currentTeam.get(i).isEnemy() && currentUnit.isEnemy()) {
                    selectUnit = currentTeam.get(i);
                }
            }

            if (currentTeam.get(i) == selectUnit) {
                batch.draw(selectUserArrow, selectUnit.getPosition().x, selectUnit.getPosition().y - 50, 100, 100);
            }
        }

    }

    private void update(float dt) {
        fx.update(dt);
        stage.act(dt);
        if (inputHandler.isTouched()) {
            fx.setup(inputHandler.getX(), inputHandler.getY());
        }

        for (int i = 0; i < startTeam.size(); i++) {
            startTeam.get(i).update(dt);
        }

        if (timeBetweenStep > 0) timeBetweenStep -= dt;

        steps();
        GameText.update(dt);
    }

    public void createGUI() {
        stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        skin = new Skin();

        skin.add(BTN_ATTACK, Assets.getInstance().getAssetManager().get("sword.png"));
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = skin.newDrawable(BTN_ATTACK, Color.WHITE);
        buttonStyle.down = skin.newDrawable(BTN_ATTACK, Color.RED);
        skin.add(BTN_ATTACK, buttonStyle);


        Button button = new Button(skin, BTN_ATTACK);
        button.setPosition(25, 25);
        button.setSize(100,100);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Boom");
            }
        });

        Group group = new Group();
        group.setPosition(30, 30);
        Image image = new Image(Assets.getInstance().getAssetManager().get(Assets.ACTION_PANEL_PNG, Texture.class));
        group.addActor(image);
        group.addActor(button);



        stage.addActor(group);
    }

    private void steps() {
        if (timeBetweenStep <= 0) {
            currentUnit.updateBonus(); // Обновляем бонусы
            if (!currentUnit.isEnemy()) { // если ходит игрок

            } else { // если ходит пк
                if (AI.isWalk()) nextUnitStep();
            }
            removeDeadUnit();
        }
    }

    private void nextUnitStep() {
        step++;
        if (step >= currentTeam.size()) {
            step = 0; //круг ходов закончен
        }
        timeBetweenStep = TIME_BETWEEN_STEP;
        currentUnit = currentTeam.get(step);
    }

    private void removeDeadUnit() {
        currentUnit.isDead();
        for (int i = 0; i < currentTeam.size(); i++) {
            if (!currentTeam.get(i).isAlive()) {
                currentTeam.remove(currentTeam.get(i));
            }
        }
    }

    private void selectUnit() {
//        for (int i = 0; i < currentTeam.size(); i++) {
//            if (InputHandler.isClick(currentTeam.get(i).getFormUnit())) {
//                selectUnit = currentTeam.get(i);
//            }
//        }
        isWin();
    }


    @Override
    public void resize(int width, int height) {
        ScreenManager.getInstance().resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        GameText.dispose();
        batch.dispose();
        currentUserArrow.dispose();
        for (int i = 0; i < startTeam.size(); i++) {
            startTeam.get(i).dispose();
        }
    }

    private void isWin() {
        int player = 0;
        int ai = 0;

        for (int i = 0; i < currentTeam.size(); i++) {
            if (currentTeam.get(i).isEnemy()) ai++;
            else player++;
        }

        if (player == 0) GameText.win("Enemy");
        if (ai == 0) GameText.win("Player");

    }

}
