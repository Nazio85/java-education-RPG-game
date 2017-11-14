package pro.x_way.skils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pro.x_way.Calculator;
import pro.x_way.units.Unit;

public class MeleeAttackSkill extends Skill {
    public static String name = "meleeAttack";

    public MeleeAttackSkill(Unit currentUnit, Unit targetUnit) {
        super(currentUnit, targetUnit);
    }

    @Override
    protected void action() {
        int dmg = currentUnit.getStrength() - targetUnit.getDefence();
        if (dmg < 0) dmg = 0;

        int totalDamage = Calculator.getTotalDamage(dmg);
        targetUnit.setHp(totalDamage, Unit.TYPE_DAMAGE);}

    @Override
    public void render(SpriteBatch batch) {
        currentUnit.setRotationDx(ROTATION_DX * (float) Math.sin((1f - skillAnimationTime) * 3.14f));
        Color color = new Color(Color.rgba8888(1f, 1f - skillAnimationTime, 1f - skillAnimationTime, 1));
        targetUnit.setUnitColor(color); //покраснение
    }




}
