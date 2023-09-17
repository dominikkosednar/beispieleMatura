import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperations extends JFrame {
    private JButton saveButton;
    private JButton copyButton;
    private JButton deleteButton;

    public FileOperations() {
        setTitle("File Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        saveButton = new JButton("Save File");
        copyButton = new JButton("Copy File");
        deleteButton = new JButton("Delete File");

        add(saveButton);
        add(copyButton);
        add(deleteButton);

        saveButton.addActionListener(new SaveFileAction());
        copyButton.addActionListener(new CopyFileAction());
        deleteButton.addActionListener(new DeleteFileAction());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class SaveFileAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("suggestedName.txt"));
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    Files.writeString(selectedFile.toPath(), "Your Content Here");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class CopyFileAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File sourceFile = fileChooser.getSelectedFile();
                Path sourcePath = sourceFile.toPath();
                Path targetPath = Paths.get(sourceFile.getParent(), "copy_" + sourceFile.getName());

                try {
                    Files.copy(sourcePath, targetPath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class DeleteFileAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    Files.delete(selectedFile.toPath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


}
