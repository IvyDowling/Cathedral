package combatsystem;

public abstract class Action {

    private Entity spark, target;

    //Always check dex to get priotiry
    public Action(Entity atkr, Entity victm) {
        spark = atkr;
        target = victm;
    }

    public Entity getSpark() {
        return spark;
    }

    public Entity getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return getSpark().toString() + " with " + getTarget().toString();
    }

}
