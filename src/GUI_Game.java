package src;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;



public class GUI_Game extends JFrame {



    Weapon claws = new Weapon("claws", false, 7, 2, WeaponType.BLADE);
    Weapon fire_breath = new Weapon("fire breath", true, 6, 4, WeaponType.FIRE);
    Weapon axe = new Weapon("axe", false, 7, 3, WeaponType.BLADE);
    Weapon hammer = new Weapon("hammer", false, 8, 2, WeaponType.IMPACT);
    Weapon sword = new Weapon("sword", false, 5, 2, WeaponType.BLADE);
    Weapon bow = new Weapon("bow", true, 5, 4, WeaponType.PIERCE);

    DefaultTableModel tableModel;
    private JList list1;
    private JPanel panel1;
    private JTable table1;
    private JCheckBox checkBox1;
    private JLabel test1;
    private JLabel test2;
    private JLabel test3;
    private JLabel test4;
    private JScrollPane ScrollPain;


    public GUI_Game() {
        setTitle("Mythical Game Units");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 200, 500, 600);

        List<Weapon> drakeBurnerWeapons = new ArrayList<>();
        drakeBurnerWeapons.add(claws);
        drakeBurnerWeapons.add(fire_breath);

        List<Weapon> dwarvishFighterWeapons = new ArrayList<>();
        dwarvishFighterWeapons.add(axe);
        dwarvishFighterWeapons.add(hammer);

        List<Weapon> elvishArcherWeapons = new ArrayList<>();
        elvishArcherWeapons.add(sword);
        elvishArcherWeapons.add(bow);

        Unit drakeBurner = new Unit("Drake Burner", 42, 5, drakeBurnerWeapons);
        Unit dwarvishFighter = new Unit("Dwarvish Fighter", 38, 4, dwarvishFighterWeapons);
        Unit elvishArcher = new Unit("Elvish Archer", 29, 6, elvishArcherWeapons);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Strike");
        tableModel.addColumn("Range");
        tableModel.addColumn("Type");
        table1 = new JTable(tableModel);
        table1.setModel(tableModel);  // Stellen Sie sicher, dass table1 dieses Modell verwendet

        List<String> unitNames = new ArrayList<>();
        unitNames.add(drakeBurner.getName());
        unitNames.add(dwarvishFighter.getName());
        unitNames.add(elvishArcher.getName());

        list1.setListData(unitNames.toArray(new String[0]));

        test2.setVisible(false);
        test1.setVisible(false);
        test3.setVisible(false);
        test4.setVisible(false);

        //panel1.add(table1);
        add(panel1);
        ScrollPain.add(table1);
        panel1.add(ScrollPain);

        setVisible(true);

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableModel.setRowCount(0);
                if (list1.getSelectedValue().equals(drakeBurner.getName())) {
                    test1.setText(drakeBurner.getName());
                    test2.setText("HP: " + drakeBurner.getMax_health());
                    test3.setText("Moves: " + drakeBurner.getMoves());
                    test4.setText("Weapons");
                    for (Weapon w : drakeBurnerWeapons) {
                        tableModel.addRow(new Object[]{w.getName(), w.getStrikes(), w.isIs_ranged() ? "melee" : "ranged", w.getType()});
                    }
                    test2.setVisible(true);
                    test1.setVisible(true);
                    test3.setVisible(true);
                    test4.setVisible(true);
                }
                else if (list1.getSelectedValue().equals(dwarvishFighter.getName())) {
                    test1.setText(dwarvishFighter.getName());
                    test2.setText("HP: " + dwarvishFighter.getMax_health());
                    test3.setText("Moves: " + dwarvishFighter.getMoves());
                    test4.setText("Weapons");
                    for (Weapon w : dwarvishFighterWeapons) {
                        tableModel.addRow(new Object[]{w.getName(), w.getStrikes(), w.isIs_ranged() ? "melee" : "ranged", w.getType()});
                    }
                    test2.setVisible(true);
                    test1.setVisible(true);
                    test3.setVisible(true);
                    test4.setVisible(true);
                }
                else if (list1.getSelectedValue().equals(elvishArcher.getName())) {
                    test1.setText(elvishArcher.getName());
                    test2.setText("HP: " + elvishArcher.getMax_health());
                    test3.setText("Moves: " + elvishArcher.getMoves());
                    test4.setText("Weapons");
                    for (Weapon w : elvishArcherWeapons) {
                        tableModel.addRow(new Object[]{w.getName(), w.getStrikes(), w.isIs_ranged() ? "melee" : "ranged", w.getType()});
                    }
                    test2.setVisible(true);
                    test1.setVisible(true);
                    test3.setVisible(true);
                    test4.setVisible(true);
                } else {
                    test2.setVisible(false);
                    test1.setVisible(false);
                    test3.setVisible(false);
                    test4.setVisible(false);
                }
            }
        });



    }

}
