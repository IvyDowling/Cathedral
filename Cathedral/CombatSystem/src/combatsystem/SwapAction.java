package combatsystem;

public class SwapAction extends Action {

    private Weapon oldWeapon, newWeapon;

    public SwapAction(Entity atkr, Entity victm, Weapon oldWp, Weapon newWp) {
        super(atkr, victm);
        oldWeapon = oldWp;
        newWeapon = newWp;
    }

    public Weapon getOldWeapon() {
        return oldWeapon;
    }

    public Weapon getNewWeapon() {
        return newWeapon;
    }

    @Override
    public String toString() {
        return super.getSpark().toString() + " switching: " + oldWeapon.toString() + " with " + newWeapon.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Action a;
        try {
            a = (Action) obj;
        } catch (Exception ignore) {
            return false;
        }
        if (a == this) {
            return true;
        }
        return a.getSpark().equals(this.getSpark()) && a.getTarget().equals(this.getTarget());
    }
}
