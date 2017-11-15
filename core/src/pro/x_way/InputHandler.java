package pro.x_way;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class InputHandler implements InputProcessor {
    private Vector2 cursor;

    public InputHandler() {
        this.cursor = new Vector2(0,0);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        cursor.set(screenX, screenY);
        ScreenManager.getInstance().getViewport().unproject(cursor);
        return false;
    }

    public float getX() {
        return cursor.x;
    }

    public float getY() {
        return cursor.y;
    }

    public boolean isTouched() {
        return Gdx.input.isTouched();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public boolean isClick(Rectangle rectangle) {
         if (!isTouched()) return false;
         return rectangle.contains(cursor);
    }
}
