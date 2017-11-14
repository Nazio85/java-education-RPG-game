package pro.x_way;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;


public class InputHandler {
    private static int getX (){return Gdx.input.getX();}
    private static int getY (){return 720 - Gdx.input.getY();}

    public static boolean isClick(Rectangle rectangle){
        if (Gdx.input.justTouched()){
            if (rectangle.contains(getX(), getY())){
                return true;
            }
        }
        return false;
    }
}
