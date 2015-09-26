package combatsystem;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class WeaponInventory {

    private List<Weapon> allWeapons;
    private Weapon[] equippedWeapons = new Weapon[2];
    private int strength;

    public WeaponInventory(int str) {
        allWeapons = new LinkedList<>();
        strength = str;
    }

    public WeaponInventory(int str, List<Weapon> wepList) {
        allWeapons = new LinkedList<>();
        strength = str;
        if (wepList != null) {
            this.addWeapon(wepList);
        }
    }

    public void clearInventory() {
        allWeapons.clear();
    }

    public boolean equipWeapon(Weapon wp) {
        if (wp == null) {
            return false;
        }
        if (equippedWeapons[0] == null) {
            equippedWeapons[0] = wp;
            return true;
        }
        if ((wp.getWeight() + getCurrentEquippedWeight()) > strength) {
            //need to swap
            return false;
        } else {
            equippedWeapons[1] = wp;
            return true;
        }
    }

    public boolean swapEquippedWeapon(Weapon newWp, Weapon old) {
        if (newWp == null || old == null) {
            return false;
        }
        if (equippedWeapons[0] != null && equippedWeapons[0] == old) {
            equippedWeapons[0] = newWp;
            return true;
        }
        if (equippedWeapons[1] != null && equippedWeapons[1] == old) {
            equippedWeapons[1] = newWp;
        }
        return false;
    }

    public boolean isInWeaponList(Weapon wp) {
        return allWeapons.contains((Weapon) wp);
    }

    public boolean addWeapon(Weapon wp) {
        if (wp == null) {
            return false;
        }
        if ((wp.getWeight() + getCurrentWeaponWeight()) > strength) {
            return false;
        } else {
            allWeapons.add(wp);
            return true;
        }
    }

    public boolean addWeapon(Collection<Weapon> wps) {
        if (wps == null) {
            return false;
        }
        int currentWeight = getCurrentWeaponWeight();
        Iterator it = wps.iterator();
        while (it.hasNext()) {
            Weapon temp = (Weapon) it.next();
            if (currentWeight + temp.getWeight() > strength) {
                //too heavy, dont add + return false
                return false;
            } else {
                allWeapons.add(temp);
                currentWeight += temp.getWeight();
            }
        }
        return true;
    }

    private int getCurrentWeaponWeight() {
        int i = 0;
        int wepWeight = 0;
        if (allWeapons != null) {
            while (i < allWeapons.size()) {
                wepWeight = wepWeight + allWeapons.get(i).getWeight();
                i++;
            }
        }
        return wepWeight;
    }

    private int getCurrentEquippedWeight() {
        int i = 0;
        int wepWeight = 0;
        while (i < 2) {
            if (equippedWeapons[i] != null) {
                wepWeight = wepWeight + equippedWeapons[i].getWeight();
            }
            i++;
        }
        return wepWeight;
    }

    public List<Weapon> getWeapons() {
        return allWeapons;
    }

    public Weapon[] getEquippedWeapons() {
        return equippedWeapons;
    }

    @Override
    public String toString() {
        String out = "Inventory contains: ";
        if (allWeapons.isEmpty()) {
            out += "no weapons";
        } else {
            for (Weapon w : allWeapons) {
                out += w.toString();
            }
        }
        return out;
    }
}
