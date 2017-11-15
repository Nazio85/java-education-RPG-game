package pro.x_way;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by alex on 14.11.17.
 */

public class Assets {
    private static final Assets ourInstance = new Assets();
    public static final String ACTION_PANEL_PNG = "actionPanel.png";
    public static final String BACKGROUND_PNG = "background.png";

    public static Assets getInstance() {
        return ourInstance;
    }

    private Assets() {
        assetManager = new AssetManager();
    }

    private AssetManager assetManager;

    public void loadAssets (ScreenManager.ScreenType screenType){
        switch (screenType){
            case BATTLE:
                assetManager.load(BACKGROUND_PNG, Texture.class);
                assetManager.load(ACTION_PANEL_PNG, Texture.class);
                assetManager.load("hero.png", Texture.class);
                assetManager.load("charSkeleton.png", Texture.class);
                assetManager.load("current_user_arrow.png", Texture.class);
                assetManager.load("select_user_arrow.png", Texture.class);
                assetManager.load("sword.png", Texture.class);
                assetManager.load("wait.png", Texture.class);
                assetManager.finishLoading();
                break;
        }
    }

    public void clear(){
        assetManager.clear();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
