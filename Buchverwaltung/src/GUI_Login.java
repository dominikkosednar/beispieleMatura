import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GUI_Login extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JButton loginButton;
    private JPanel JPanelMain;

    private Nutzer selectedNutzer;

    public GUI_Login(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(800, 200);
        add(JPanelMain);
        pack();
        setVisible(true);

        List<Nutzer> myNutzer = new ArrayList<>();
        myNutzer.add(new Nutzer(1, "Mario"));
        myNutzer.add(new Nutzer(2, "Jenni"));

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String Name = textField1.getText();
                int ID = Integer.valueOf(textField2.getText());

                for (Nutzer n : myNutzer){
                    if(n.getUserID() == ID && n.getName().equals(Name)){
                        selectedNutzer = n;
                        System.out.println(selectedNutzer.toString());
                        GUI_Bibliothek myBib = new GUI_Bibliothek(selectedNutzer);
                        dispose();
                    }
                }
            }
        });
    }
}
