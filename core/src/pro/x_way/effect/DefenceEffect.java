package pro.x_way.effect;

import com.badlogic.gdx.graphics.Color;

import pro.x_way.GameText;
import pro.x_way.units.*;

public class DefenceEffect extends Effect {
    public static final int TIME = 1;
    public static final int DEFENCE_VALUE = 5;
    private Unit unit;

    public DefenceEffect(Unit unit) {
        super();
        this.unit = unit;
    }

    @Override
    public void start(int time) {
        super.start(TIME);
        unit.setDefence(DEFENCE_VALUE);
        printEffect();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void end() {
        super.end();
        unit.setDefence(DEFENCE_VALUE * -1);
        GameText.printEffectText(unit, Color.BLUE, "Defence Off");
    }

    public void printEffect(){
        GameText.printEffectText(unit, Color.BLUE, "Defence On");
    }
}
