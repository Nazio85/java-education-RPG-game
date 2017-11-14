package pro.x_way.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Skeleton extends Unit{
    public Skeleton(String name, Vector2 vector2, boolean isEnemy) {
        super(vector2, new Texture("charSkeleton.png"),
                7,1,3, 4,
                1, 1, 1, isEnemy);
        this.level = 1;
        this.name = name;
    }
}
