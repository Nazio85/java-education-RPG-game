package pro.x_way;


import java.util.Comparator;

import pro.x_way.units.Unit;

public class SortedByInitiative implements Comparator<Unit> {
    @Override
    public int compare(Unit o1, Unit o2) {
        int x1 = o1.getInitiative();
        int x2 = o2.getInitiative();

        if (x1 > x2) return -1;
        else if (x1 < x2) return 1;
        else return 0;

    }
}
