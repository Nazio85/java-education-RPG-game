package pro.x_way.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import pro.x_way.InputHandler;

public class Button {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private Texture texture;
    private Rectangle rectangle;
    private int x;
    private int y;

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Button(String texturePath, int x, int y) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public boolean isCLickButton() {
        return InputHandler.isClick(rectangle);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, WIDTH, HEIGHT);
    }

}
