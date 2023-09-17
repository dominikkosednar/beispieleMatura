package src;

public class Weapon {

    private String name;
    private boolean is_ranged;
    private int damage;
    private int strikes;
    private WeaponType type;

    public Weapon() {
    }

    public Weapon(String name, boolean is_ranged, int damage, int strikes, WeaponType type) {
        this.name = name;
        this.is_ranged = is_ranged;
        this.damage = damage;
        this.strikes = strikes;
        this.type = type;
    }

    public int getPower() {
        return strikes * damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_ranged() {
        return is_ranged;
    }

    public void setIs_ranged(boolean is_ranged) {
        this.is_ranged = is_ranged;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getStrikes() {
        return strikes;
    }

    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }
}
