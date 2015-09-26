package combatsystem;

public class Weapon {

    private boolean isSharp;
    private int length;
    private int weight;
    private int sharpnessBonus;

    public Weapon(int l, int w, boolean sharp, int sharpBonus) {
        setLength(l);
        setWeight(w);
        setSharp(sharp, sharpBonus);
    }

    public Weapon(int l, int w) {
        setLength(l);
        setWeight(w);
        setSharp(false, 0);
    }

    private void setLength(int l) {
        if (l >= 0) {
            length = l;
        }
    }

    private void setWeight(int w) {
        if (w >= 0) {
            weight = w;
        }
    }

    private void setSharp(boolean sh, int bonus) {
        isSharp = sh;
        if (sh && bonus > 0) {
            sharpnessBonus = bonus;
        } else {
            sharpnessBonus = 0;
        }
    }

    public boolean isIsSharp() {
        return isSharp;
    }

    public int getLength() {
        return length;
    }

    public int getWeight() {
        return weight;
    }

    public int getSharpnessBonus() {
        if (isSharp) {
            return sharpnessBonus;
        }
        return 0;
    }
    
    @Override
    public String toString(){
        return "length: " + getLength() + "weight: " + getWeight() + "sharp? " + isIsSharp() + " bonus " + getSharpnessBonus();
    }
}
