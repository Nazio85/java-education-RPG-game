package pro.x_way;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleBackGround {
    private Texture background;

    public BattleBackGround() {
        this.background = new Texture("background.png");
    }

    public void render (SpriteBatch batch){
        batch.draw(background,
                0,0,
                1280, 720);
    }


    public void dispose (){
        background.dispose();
    }
}
