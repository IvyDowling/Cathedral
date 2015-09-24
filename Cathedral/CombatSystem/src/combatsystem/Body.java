package combatsystem;

public class Body {

    private int height, weight;
    BodyPart lArm, rArm, lLeg, rLeg, torso;

    public Body(int h, int w) {
        height = h;
        weight = w;
        lArm = new BodyPart(BodyComponent.LARM);
        rArm = new BodyPart(BodyComponent.RARM);
        lLeg = new BodyPart(BodyComponent.LLEG);
        rLeg = new BodyPart(BodyComponent.RLEG);
        torso = new BodyPart(BodyComponent.TORSO);
    }

    public BodyPart getBodyPart(BodyComponent c) {
        switch (c) {
            case RARM:
                return rArm;
            case LARM:
                return lArm;
            case LLEG:
                return lLeg;
            case RLEG:
                return rLeg;
            case TORSO:
                return torso;
            default:
                return null;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        Body temp;
        try {
            temp = (Body) obj;
        } catch (Exception ignore) {
            return false;
        }
        if (temp == this) {
            return true;
        }
        //check the height, weight, and compare every body part.
        if (temp.getHeight() == this.getHeight() && temp.getWeight() == this.getWeight()) {
            if (!temp.lArm.equals(this.lArm)) {
                return false;
            }
            if (!temp.rArm.equals(this.rArm)) {
                return false;
            }
            if (!temp.lLeg.equals(this.lLeg)) {
                return false;
            }
            if (!temp.rLeg.equals(this.rLeg)) {
                return false;
            }
            if (!temp.torso.equals(this.torso)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return lArm.toString() + " " + rArm.toString() + " " + lLeg.toString() + " " + rLeg.toString() + " " + torso.toString();
    }
}
