package pro.x_way;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RPGGame extends Game {
    private SpriteBatch batch;
    private BattleScreen fightScreen;


    @Override
    public void create() {
        batch = new SpriteBatch();
        fightScreen = new BattleScreen();
        setScreen(fightScreen);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        this.getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
        fightScreen.dispose();
    }
}
