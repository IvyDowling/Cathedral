package combatsystem;

public class AttackAction extends Action {

    private BodyComponent targetBodyComponent;

    public AttackAction(Entity atkr, Entity victm, BodyComponent victimBC) {
        super(atkr, victm);
        targetBodyComponent = victimBC;
    }

    public BodyComponent getBodyComponenet() {
        return targetBodyComponent;
    }

    @Override
    public String toString() {
        return super.getSpark().toString() + " attacking " + super.getTarget().toString();
    }

    @Override
    public boolean equals(Object obj) {
        AttackAction a;
        try {
            a = (AttackAction) obj;
        } catch (Exception ignore) {
            return false;
        }
        if (a == this) {
            return true;
        }
        return a.getSpark().equals(this.getSpark()) && a.getTarget().equals(this.getTarget()) && (a.getBodyComponenet() == this.getBodyComponenet());
    }

}
