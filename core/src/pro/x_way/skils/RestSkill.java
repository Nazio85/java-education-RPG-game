package pro.x_way.skils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pro.x_way.units.Unit;

public class RestSkill extends Skill {

    public RestSkill(Unit currentUnit, Unit targetUnit) {
        super(currentUnit, targetUnit);
    }

    @Override
    public void action() {
        int countHill = (int) ((float) currentUnit.getMaxHp() / 100 * 15);
        currentUnit.setHp(countHill, Unit.TYPE_HILL);

    }

    @Override
    public void render(SpriteBatch batch) {

    }

}
