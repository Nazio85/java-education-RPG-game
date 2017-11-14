package pro.x_way.skils;


import java.util.ArrayList;
import java.util.List;

import pro.x_way.units.Unit;

public class FabricSkills {
    private static List<Skill> skillList;

    public static Skill actionMeleeAttack(Unit currentUnit, Unit unitTarget) {
        Skill skill = new MeleeAttackSkill(currentUnit, unitTarget);
        skillList.add(skill);
        return skill;
    }

    public static Skill rest(Unit currentUnit, Unit targetUnit) {
        Skill skill = new RestSkill(currentUnit, targetUnit);
        skillList.add(skill);
        return skill;
    }

    public static Skill dodge(Unit currentUnit, Unit targetUnit){
        Skill skill = new DodgeMeleeAttack(currentUnit, targetUnit);
        skillList.add(skill);
        return skill;
    }

    public static List<Skill> getAllSkills() {
        return skillList;
    }

    public static void setup() {
        skillList = new ArrayList<Skill>();
    }
}
