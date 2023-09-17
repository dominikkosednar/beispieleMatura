// CharacterInfo.java
// CharacterInfoGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class CharacterInfo {
    protected char character;

    public CharacterInfo(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}

// ASCIICharacter.java
class ASCIICharacter extends CharacterInfo {
    private int asciiCode;
    private String hexValue;

    public ASCIICharacter(char character) {
        super(character);
        this.asciiCode = (int) character;
        this.hexValue = Integer.toHexString(character);
    }

    public int getAsciiCode() {
        return asciiCode;
    }

    public String getHexValue() {
        return hexValue;
    }
}



class CharacterInfoGUI extends JFrame {
    private JTextField inputField;
    private JLabel asciiLabel;
    private JLabel hexLabel;
    private JButton convertButton;

    public CharacterInfoGUI() {
        setTitle("Character Info");
        setSize(300, 200);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        asciiLabel = new JLabel("ASCII: ");
        hexLabel = new JLabel("Hex: ");
        convertButton = new JButton("Convert");

        add(inputField);
        add(asciiLabel);
        add(hexLabel);
        add(convertButton);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                if (input.length() > 0) {
                    char ch = input.charAt(0);
                    ASCIICharacter asciiCharacter = new ASCIICharacter(ch);
                    asciiLabel.setText("ASCII: " + asciiCharacter.getAsciiCode());
                    hexLabel.setText("Hex: " + asciiCharacter.getHexValue());
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CharacterInfoGUI().setVisible(true);
        });
    }
}
