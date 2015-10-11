package cathedral;

import combatsystem.Entity;
import combatsystem.Weapon;
import java.util.List;

public class Save implements java.io.Serializable {

    public double height;
    public int weight, str, dex, enemyNumber;
    public List<Weapon> weapons;

    public Save(double h, int w, int s, int d, List<Weapon> wep, int curEn) {
        height = h;
        weight = w;
        str = s;
        dex = d;
        weapons = wep;
        enemyNumber = curEn;
    }

    public Entity getEntity() {
        return new Entity(height, weight, str, dex, weapons);
    }
}
