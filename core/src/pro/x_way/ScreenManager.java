package pro.x_way;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by alex on 14.11.17.
 */

public class ScreenManager {

    public static final int WORLD_WIDTH = 1280;
    public static final int WORLD_HEIGHT = 720;

    private RPGGame rpgGame;
    private BattleScreen battleScreen;
    private Viewport viewport;


    public enum ScreenType{
        MENU, BATTLE;
    }
    private static final ScreenManager ourInstance = new ScreenManager();
    public static ScreenManager getInstance() {
        return ourInstance;
    }



    public void init (RPGGame rpgGame, SpriteBatch batch){
        this.rpgGame = rpgGame;
        this.battleScreen = new BattleScreen(batch);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewport.update(WORLD_WIDTH,WORLD_HEIGHT, true);
        viewport.apply();

    }

    public void resize (int width, int height){
        viewport.update(width, height, true);
        viewport.apply();
    }

    private ScreenManager() {
    }

    public void switchType (ScreenType screenType){
        Screen screen = rpgGame.getScreen();
        if (screen != null) dispose();

        switch (screenType){
            case BATTLE:
                Assets.getInstance().loadAssets(screenType);
                rpgGame.setScreen(battleScreen);
                break;
        }
    }

    public void dispose (){
        Assets.getInstance().clear();
        Assets.getInstance().getAssetManager().dispose();
    }

    public Viewport getViewport() {
        return viewport;
    }
}
