package cathedral;

import combatsystem.*;

public class EnemyLib {

    private final static Weapon[] dexWep = {
        new Weapon("Dagger", 1, 2, 5),
        new Weapon("Dagger", 1, 2, 5)
    };

    private final static Entity[] enemyLib = new Entity[]{
        new Entity(5.5, 180, 10, 6, new Weapon("Axe", 3, 8, 2)), //guard
        new Entity(6.0, 280, 14, 4, new Weapon("Hammer", 2, 10, 0)), //str
        new Entity(5.0, 280, 6, 16, dexWep) //dex
    };

    public static Entity getEnemy(int i) {
        if (i < enemyLib.length) {
            return enemyLib[i];
        }
        return null;
    }
}
