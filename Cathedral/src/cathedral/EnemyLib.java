package cathedral;

import combatsystem.*;

public class EnemyLib {

    private final static Weapon[] dexWep = {
        new Weapon("Dagger", 1, 2, 5),
        new Weapon("Dagger", 1, 2, 5)
    };

    private final static Entity[] enemyLib = new Entity[]{
        new Entity(5.5, 180, 10, 6, new Weapon("Axe", 3, 8, 2)).setName("Guard"), //guard
        new Entity(6.0, 280, 14, 4, new Weapon("Hammer", 2, 10, 0)).setName("Titan"), //str
        new Entity("Viper", 5.0, 280, 6, 16, dexWep) //dex
    };

    public static Entity getEnemy(int i) {
        int enemyIndex = i;
        if (enemyIndex == 1 || enemyIndex == 2 || enemyIndex == 3) {
            enemyIndex = 0;
        }
        if (enemyIndex < enemyLib.length) {
            return enemyLib[i];
        }
        return null;
    }

    public static int getLength() {
        return enemyLib.length;
    }
}
