package pro.x_way;


import pro.x_way.skils.FabricSkills;
import pro.x_way.units.Unit;

public class AI {

    public static boolean isWalk() {
        Unit targetUnit = BattleScreen.currentTeam.get((int) (Math.random() * BattleScreen.currentTeam.size())); //рандомный выбор персонажа
        Unit currentUnit = BattleScreen.currentUnit;

        if (doYouWantHill(currentUnit)) {
            FabricSkills.rest(currentUnit, targetUnit);
            return true;
        } else {
            if (Calculator.meleeAttack(currentUnit, targetUnit)) return true;
        }
        return false;
    }

    private static boolean doYouWantHill(Unit currentUnit) {
        int x = (int) (Math.random() * 2);
        return Calculator.isLowHp(currentUnit) && x == 1;
    }


}
