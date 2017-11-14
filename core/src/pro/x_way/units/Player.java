package pro.x_way.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends Unit {
    public Player(String name, Vector2 vector2, boolean isEnemy) {
        super(vector2, new Texture("hero.png"),
                9,2,7,
                3,2,1,7, isEnemy);
        this.name = name;
        this.level = 1;
    }
}
