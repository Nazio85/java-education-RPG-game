package pro.x_way.skils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pro.x_way.units.Unit;

public class DodgeMeleeAttack extends Skill{
    public DodgeMeleeAttack(Unit currentUnit, Unit targetUnit) {
        super(currentUnit, targetUnit);
    }

    @Override
    protected void action() {
        targetUnit.dodged();
    }

    @Override
    public void render(SpriteBatch batch) {
        currentUnit.setRotationDx(ROTATION_DX * (float) Math.sin((1f - skillAnimationTime) * 3.14f));
        targetUnit.setRotationDx((ROTATION_DX * (float) Math.sin((1f - skillAnimationTime) * 3.14f)) * -1);
    }

}
