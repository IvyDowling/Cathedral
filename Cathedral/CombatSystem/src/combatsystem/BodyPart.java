package combatsystem;

public class BodyPart {

    private int health;
    private boolean isImpaired;
    private boolean isArmored;
    private BodyComponent component;

    public BodyPart(int h) {
        health = h;
    }

    public BodyPart(BodyComponent bc) {
        component = bc;
    }

    public BodyPart(int h, BodyComponent bc) {
        health = h;
        component = bc;
    }

    public int getHealth() {
        return health;
    }

    public boolean isIsImpaired() {
        return isImpaired;
    }

    public boolean isIsArmored() {
        return isArmored;
    }

    public BodyComponent getComponent() {
        return component;
    }

    @Override
    public boolean equals(Object obj) {
        BodyPart temp;
        try {
            temp = (BodyPart) obj;
        } catch (Exception ignore) {
            return false;
        }
        if (temp == this) {
            return true;
        }
        return (temp.isIsArmored() == this.isIsArmored() && temp.isIsImpaired() == this.isIsImpaired() && temp.getComponent() == this.getComponent());
    }

    @Override
    public String toString() {
        return component + " " + health;
    }
}
