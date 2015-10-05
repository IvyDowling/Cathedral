package combatsystem;

public class Action {

    private Entity spark, target;
    private BodyPart bodyPart;
    private ActionExecution actionExe;

    //Always check dex to get priotiry
    public Action(Entity atkr, Entity victm, BodyPart bp, ActionExecution e) {
        spark = atkr;
        target = victm;
        bodyPart = bp;
        actionExe = e;
    }

    public Entity getSpark() {
        return spark;
    }

    public Entity getTarget() {
        return target;
    }

    public void execute() {
        if (actionExe != null) {
            actionExe.onExecute(spark, target, bodyPart);
        }
    }

    @Override
    public String toString() {
        return getSpark().toString() + " with " + getTarget().toString() + "\n";
    }

}
