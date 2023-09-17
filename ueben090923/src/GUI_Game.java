import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GUI_Game extends JFrame{
    private JPanel MainPanel;
    private JPanel LeftJPanel;
    private JPanel MiddleJPanel;
    private JPanel RightJPanel;
    private JList myList;
    private JCheckBox myCheckbox;
    private JTable myTable;
    private JLabel Title;
    private JLabel HP;
    private JLabel Moves;
    private JLabel WeaponsTitle;
    private JLabel Image;

    private Unit selectedUnit;
    /**
     * Menu
     */
    private JMenuBar menuBar;
    private JMenu myMenu1, myMenu2;
    private JMenuItem SaveAs, Quit, About;

    Weapon weapon1 = new Weapon("claws", 7, 2, false, WeaponType.blade);
    Weapon weapon2 = new Weapon("fire breath", 6, 4, true, WeaponType.fire);
    Weapon weapon3 = new Weapon("axe", 7, 3, false, WeaponType.blade);
    Weapon weapon4 = new Weapon("hammer", 8, 2, false, WeaponType.impact);
    Weapon weapon5 = new Weapon("sword", 5, 2, false, WeaponType.blade);
    Weapon weapon6 = new Weapon("bow", 5, 4, true, WeaponType.pierce);
    Unit myUnit1 = new Unit(UnitType.DrakeBurner, "Drake Burner", 42, 5);
    Unit myUnit2 = new Unit(UnitType.DwarvishFighter, "Dwarvish Fighter", 38, 4);
    Unit myUnit3 = new Unit(UnitType.ElvishArcher, "Elvish Archer", 29, 6);


    public GUI_Game(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(600, 200);
        add(MainPanel);
        pack();
        setVisible(true);

        /**
         * add Weapons
         */
        myUnit1.addWeaponToUnit(weapon1);
        myUnit1.addWeaponToUnit(weapon2);
        myUnit2.addWeaponToUnit(weapon3);
        myUnit2.addWeaponToUnit(weapon4);
        myUnit3.addWeaponToUnit(weapon5);
        myUnit3.addWeaponToUnit(weapon6);

        /**
         * Menu
         */
        menuBar = new JMenuBar();
        myMenu1 = new JMenu("File");
        myMenu2 = new JMenu("Help");

        SaveAs = new JMenuItem("Save As..");
        Quit = new JMenuItem("Quit");
        About = new JMenuItem("About");

        menuBar.add(myMenu1);
        menuBar.add(myMenu2);
        myMenu1.add(SaveAs);
        myMenu1.add(Quit);
        myMenu2.add(About);
        setJMenuBar(menuBar);

        About.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "This is a viewer for creatures in a mythical game",
                    "About Unit GUI",
                    JOptionPane.WARNING_MESSAGE);
        });

        SaveAs.addActionListener(e -> {
            //GUI_Save mySaveGUI = new GUI_Save(getSelectedUnit());
            SaveFileChooser myFileChooser = new SaveFileChooser(selectedUnit);
            myFileChooser.saveFile();
        });

        SaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));

        Quit.addActionListener(e -> {
            System.exit(0);
        });

        /**
         * List
         */
        String[] characters = {myUnit1.getName(), myUnit2.getName(), myUnit3.getName()};
        myList.setListData(characters);
        myList.setSelectedIndex(0);

        /**
         * selected Unit
         */
        updateTable(getSelectedUnit());

        myList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedUnit = getSelectedUnit();
                updateTable(selectedUnit);
            }
        });

        /**
         * Checkbox + image visible or not
         */
        myCheckbox.setSelected(true);

        myCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!myCheckbox.isSelected()){
                    MiddleJPanel.setVisible(false);
                } else {
                    MiddleJPanel.setVisible(true);
                }
            }
        });
    }


    public Unit getSelectedUnit(){
        if(myList.getSelectedValue().equals("Drake Burner")){
            selectedUnit = myUnit1;
            selectedUnit.setPath("images/drake_burner.png");
        } else if (myList.getSelectedValue().equals("Dwarvish Fighter")) {
            selectedUnit = myUnit2;
            selectedUnit.setPath("images/dwarvish_fighter.png");
        } else if (myList.getSelectedValue().equals("Elvish Archer")) {
            selectedUnit = myUnit3;
            selectedUnit.setPath("images/elvish_archer.png");
        }

        return selectedUnit;
    }

    public void updateTable(Unit selectedUnit){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Strikes");
        model.addColumn("Range");
        model.addColumn("Type");
        myTable.setModel(model);
        model.setRowCount(0);

        Title.setText(selectedUnit.getName());
        HP.setText(String.valueOf(selectedUnit.getHitpoints()));
        Moves.setText(String.valueOf(selectedUnit.getMoves()));
        WeaponsTitle.setText("Weapons");

        for(Weapon weapon : selectedUnit.getWeapons()){
            model.addRow(new Object[]{weapon.getName(), weapon.getWeaponDamage(), weapon.isRange() ? "meele" : "ranged", weapon.getType()});
        }

        ImageIcon icon = new ImageIcon(getClass().getResource(selectedUnit.getPath()));
        Image.setIcon(icon);
    }



}
