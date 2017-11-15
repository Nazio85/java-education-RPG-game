package pro.x_way;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.xml.soap.Text;

public class RPGGame extends Game {
    private SpriteBatch batch;


    @Override
    public void create() {
        batch = new SpriteBatch();

        ScreenManager.getInstance().init(this, batch);
        ScreenManager.getInstance().switchType(ScreenManager.ScreenType.BATTLE);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        this.getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }


}
