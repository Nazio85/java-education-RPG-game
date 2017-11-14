package pro.x_way;


import pro.x_way.skils.FabricSkills;
import pro.x_way.units.Unit;

public class Calculator {
    public static boolean isMiss(Unit attacker, Unit targetUnit) {
        if (targetUnit.getDexterity() - attacker.getDexterity() < 0 || // this.dexterity -- атакующий
                targetUnit.getDexterity() - attacker.getDexterity() == 0) {
            return (int) (Math.random() * 10) <= 9;
        } else {
            int difference = targetUnit.getDexterity() - attacker.getDexterity(); // во сколько раз принимающий атаку ловчей
            if ((int) (Math.random() * 10) > difference) return true;
            else return false;
        }
    }

    public static int calculateStatusBar(int maxStatus, int currentStatus) {
        float percentTexture = Unit.FULL_STATUS / 100f;
        float percentStatus = currentStatus * 100 / maxStatus;
        return (int) (percentTexture * percentStatus);
    }

    public static boolean isLowHp(Unit currentUnit) {
        float result = (float) currentUnit.getHp() * 100 / currentUnit.getMaxHp();
        if (result < 40) return true;
        else return false;
    }

    public static int getTotalDamage(int dmg) {
        return (int) (dmg * 0.8f + (float) dmg * Math.random() * 0.5f);
    }

    public static boolean meleeAttack(Unit currentUnit, Unit unitTarget) {
        if (unitTarget.isEnemy() && !currentUnit.isEnemy()
                || !unitTarget.isEnemy() && currentUnit.isEnemy()) {
            if (Calculator.isMiss(currentUnit, unitTarget)) {
                FabricSkills.actionMeleeAttack(currentUnit, unitTarget);
            } else {
                FabricSkills.dodge(currentUnit, unitTarget);
            }
            return true;
        }

        return false;
    }
}
