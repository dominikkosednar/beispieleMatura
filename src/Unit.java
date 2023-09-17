package src;

import java.util.ArrayList;
import java.util.List;

public class Unit {

    private String name;
    private int max_health;
    private int moves;
    private List<Weapon> weapons;


    public Unit(String name, int max_health, int moves, List<Weapon> weapons) {
        this.name = name;
        this.max_health = max_health;
        this.moves = moves;
        this.weapons = weapons;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax_health() {
        return max_health;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
}
