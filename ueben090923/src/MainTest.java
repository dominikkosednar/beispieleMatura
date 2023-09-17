import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainTest {
    @Test
    public void testAvailableUnits() {
        Player Mario = new Player("Mario");

        Weapon weapon01 = new Weapon("claws", 7, 2, false, WeaponType.blade);
        Weapon weapon02= new Weapon("fire breath", 6, 4, true, WeaponType.fire);
        Weapon weapon03 = new Weapon("axe", 7, 3, false, WeaponType.blade);
        Weapon weapon04 = new Weapon("hammer", 8, 2, false, WeaponType.impact);
        Weapon weapon05 = new Weapon("sword", 5, 2, false, WeaponType.blade);
        Weapon weapon06 = new Weapon("bow", 5, 4, true, WeaponType.pierce);
        Unit myUnit01 = new Unit(UnitType.DrakeBurner, "Drake Burner", 42, 5);
        Unit myUnit02 = new Unit(UnitType.DwarvishFighter, "Dwarvish Fighter", 38, 4);
        Unit myUnit03 = new Unit(UnitType.ElvishArcher, "Elvish Archer", 29, 6);

        myUnit01.addWeaponToUnit(weapon01);
        myUnit01.addWeaponToUnit(weapon02);
        myUnit02.addWeaponToUnit(weapon03);
        myUnit02.addWeaponToUnit(weapon04);
        myUnit03.addWeaponToUnit(weapon05);
        myUnit03.addWeaponToUnit(weapon06);

        Mario.addUnitsToAllUnits(myUnit01);
        Mario.addUnitsToAllUnits(myUnit02);
        Mario.addUnitsToAllUnits(myUnit03);

        Mario.addUnitsToFigthingUnits(myUnit01);

        List<Unit> availableUnits = Main.availableUnits(Mario.getAllUnits(), Mario.getFightingUnits());
        int expectedNumber = 2;
        Assertions.assertEquals(2, availableUnits.size());

        //Assertions.assertEquals(availableUnits.contains(myUnit02), true);
    }
}
