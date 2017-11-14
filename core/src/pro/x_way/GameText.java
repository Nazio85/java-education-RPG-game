package pro.x_way;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import pro.x_way.units.Unit;

public class GameText {
    public static final String MISS = "Miss";
    private static BitmapFont font;
    private static SpriteBatch batch;
    private static float dt;
    private static FlyingText flyingText;


    public static void gameTextSetup() {
        font = new BitmapFont();
        flyingText = new FlyingText();
//        font = new BitmapFont(Gdx.files.internal("font.fnt"));
    }

    public GameText() {
    }

    public static void update(float dt) {
        GameText.dt = dt;
        flyingText.update();
    }

    public static void render(SpriteBatch batch) {
        GameText.batch = batch;
    }

    public static void printFlyingText(SpriteBatch batch) {
        if (flyingText.active) flyingText.render(batch);
    }

    //BattleScreen Place
    public static void win(String winner) {
        font.draw(batch, winner + " WIN", 600, 420);
    }

    //BattleScreen
    public static void damage(Unit unit, int lastDamage) {
        flyingText.add(unit, String.valueOf(lastDamage), Color.RED);
    }

    public static void miss(Unit unit) {
        flyingText.add(unit, String.valueOf(MISS), Color.WHITE);
    }


    public static void hill(Unit unit, int countHill) {
        flyingText.add(unit, String.valueOf(countHill), Color.GREEN);
    }


    //StatusBar
    public static void printStatusBar(Unit unit, SpriteBatch batch) {
        Vector2 position = unit.getPosition();
        font.draw(batch, String.valueOf(unit.getHp()), position.x, position.y + unit.getUnitHeight() + Unit.HEIGHT_STATUS_BAR_ROW , Unit.FULL_STATUS, 1, false);
        font.draw(batch, String.valueOf(unit.getMp()), position.x, position.y + unit.getUnitHeight(), Unit.FULL_STATUS, 1, false);
        font.setColor(Color.WHITE);
    }

    public static void printEffectText(Unit unit, Color color, String message) {
        flyingText.add(unit, message, color);
    }

    public static void dispose() {
        font.dispose();
    }

    static class FlyingText {
        public static final float FULL_TIME_ANIMATION = 1f;
        private float time;
        private String text;
        private boolean active;
        float x;
        float y;
        private Color color;

        public FlyingText() {
            this.text = "";
            this.time = 0.0f;
            this.color = Color.WHITE;
        }

        public void add(Unit unit, String text, Color color) {
            this.text = text;
            this.time = FULL_TIME_ANIMATION;
            this.active = true;
            this.color = color;
            x = getCenterTextureX(unit);
            y = getCenterTextureY(unit);

        }

        public void update() {
            if (time > 0) {
                time -= dt;
            } else active = false;
        }

        public void render(SpriteBatch batch) {
            font.setColor(color);
            setRotation();
            font.draw(batch, text, x, y);
            font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        }

        private void setRotation() {
            x += time;
            y += time * 2;
        }

        public float getCenterTextureX(Unit unit) {
            return unit.getPosition().x + unit.getUnitView().getWidth() / 2;
        }

        public float getCenterTextureY(Unit unit) {
            return unit.getPosition().y + unit.getUnitView().getWidth() / 2 + 25;
        }


    }
}
