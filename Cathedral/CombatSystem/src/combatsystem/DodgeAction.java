package combatsystem;

public class DodgeAction extends Action {
    
    public DodgeAction(Entity atkr) {
        super(atkr, null);
    }
    
    @Override
    public String toString() {
        return " dodging";
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
