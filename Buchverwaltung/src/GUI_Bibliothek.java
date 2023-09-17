import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI_Bibliothek extends JFrame{
    private JPanel panel1;
    private JScrollPane myScrollPane1;
    private JTable myTable1;
    private JButton zurueckbringenButton;
    private JButton ausborgenButton;
    private JTable myTable2;
    private JTextField Ueberschrift_Zurueckbringen;
    private JTextField Ueberschrift_Ausborgen;


    private JMenuBar menuBar;
    private JMenu myFile;
    private JMenu Help;

    private JMenuItem SaveAs, Quit, About;


    private List<Buch> moeglicheAusborgen;
    private List<AusgeliehenesBuch> moeglicheZurueckgeben;

    private Nutzer actNutzer;
    private List<Buch> books;

    public GUI_Bibliothek(Nutzer actNutzer){
        this.actNutzer = actNutzer;
        this.books = new ArrayList<>();

        books.add(new Buch(1, "Dead", "Mario", true));
        books.add(new Buch(2, "Alive", "Luigi", true));
        books.add(new Buch(3, "Gone", "Peach", true));
        books.add(new Buch(4, "Here", "Daisy", true));
        books.add(new Buch(5, "Far", "Yoshi", true));
        books.add(new Buch(6, "Near", "Bowser", true));
        books.add(new Buch(7, "Lost", "Wario", true));
        books.add(new Buch(8, "Found", "Waluigi", true));
        books.add(new Buch(9, "Up", "Toad", true));
        books.add(new Buch(10, "Down", "Toadette", true));

        System.out.println(actNutzer.toString());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(800, 200);
        add(panel1);
        pack();
        setVisible(true);

        /**
         * Alle Bücher
         */


        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                new Object[]{"ISBN", "Title", "Author", "Checkbox"}) {
            Class[] types = new Class[] { Integer.class, String.class, String.class, Boolean.class };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        myTable1.setModel(model);

        for(Buch buch : books){
            model.addRow(new Object[]{buch.getISBN(), buch.getTitle(), buch.getAuthor(), false});
        }

        moeglicheAusborgen = new ArrayList<>();

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 3) {  // Die Checkbox-Spalte
                    Boolean checked = (Boolean) model.getValueAt(row, column);
                    if (checked != null && checked) {
                        moeglicheAusborgen.add(books.get(row));
                    } else if (!checked || checked == null){
                        moeglicheAusborgen.remove(books.get(row).getTitle());
                    }
                }
            }
        });

        /**
         * Menu
         */
        menuBar = new JMenuBar();
        myFile = new JMenu("File");
        Help = new JMenu("Help");
        SaveAs = new JMenuItem("Save As..");
        Quit = new JMenuItem("Quit");
        About = new JMenuItem("About");

        menuBar.add(myFile);
        menuBar.add(Help);

        myFile.add(SaveAs);
        myFile.add(Quit);
        Help.add(About);

        setJMenuBar(menuBar);

        /**
         * Table mit den ausgeborgten Büchern
         */
        DefaultTableModel model1 = new DefaultTableModel(new Object[][]{},
                new Object[]{"ISBN", "Title", "Author", "Checkbox"}) {
            Class[] types = new Class[] { Integer.class, String.class, String.class, Boolean.class };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        myTable2.setModel(model1);

        for(AusgeliehenesBuch mybook : actNutzer.getBorrowedBooks()){
            model1.addRow(new Object[]{mybook.getISBN(), mybook.getTitle(), mybook.getAuthor(), false});
        }

        /**
         * Button zum ausborgen + aktualisieren
         */
        ausborgenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Buch buch : moeglicheAusborgen){
                    buch.setFree(false);
                    AusgeliehenesBuch myBooks = new AusgeliehenesBuch(buch);
                    actNutzer.addBook(myBooks);
                }
                model1.setRowCount(0);
                for(AusgeliehenesBuch mybook : actNutzer.getBorrowedBooks()){
                    model1.addRow(new Object[]{mybook.getISBN(), mybook.getTitle(), mybook.getAuthor(), false});
                }
                model.setRowCount(0);
                for(Buch buch : books){
                    if (buch.isFree() ){
                        model.addRow(new Object[]{buch.getISBN(), buch.getTitle(), buch.getAuthor(), false});
                    }
                }
                moeglicheAusborgen.clear();
            }
        });

        /**
         * Zurückgeben button
         */
        moeglicheZurueckgeben = new ArrayList<>();

        zurueckbringenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (AusgeliehenesBuch buch : moeglicheZurueckgeben){
                    actNutzer.gibBuchZurueck(buch);  // Verwenden Sie die vorhandene Methode, um ein Buch zurückzugeben
                    
                    // Aktualisieren Sie den "free"-Status des Buches in der Hauptbuchliste
                    for (Buch mainBook : books) {
                        if (mainBook.getISBN() == buch.getISBN()) {
                            mainBook.setFree(true);
                            break;
                        }
                    }
                }
                model1.setRowCount(0);
                for(AusgeliehenesBuch mybook : actNutzer.getBorrowedBooks()){
                    model1.addRow(new Object[]{mybook.getISBN(), mybook.getTitle(), mybook.getAuthor(), false});
                }
                model.setRowCount(0);
                for(Buch buch : books){
                    if (buch.isFree()){
                        model.addRow(new Object[]{buch.getISBN(), buch.getTitle(), buch.getAuthor(), false});
                    }
                }
                moeglicheZurueckgeben.clear();
            }
        });


        model1.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 3) {  // Die Checkbox-Spalte
                    Boolean checked = (Boolean) model1.getValueAt(row, column);
                    AusgeliehenesBuch myBooks = actNutzer.getBorrowedBooks().get(row);  // Holen Sie sich das Buch direkt aus der Liste des Nutzers
                    if (checked != null && checked) {
                        moeglicheZurueckgeben.add(myBooks); // Fügen Sie das Buch der Liste hinzu
                        System.out.println(myBooks.getTitle());
                    } else if (!checked || checked == null){
                        moeglicheZurueckgeben.remove(myBooks); // Entfernen Sie das Buch aus der Liste
                    }
                }
            }
        });



        SaveAs.addActionListener(e -> {
            File fixedPath = new File("./Markdown/" + actNutzer.getName() + ".md");
            saveBooks(fixedPath);
            System.out.println("Alles wurde gespeichert!");
        });
    }

    private void saveBooks(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write("# " + actNutzer.getName() + "\n");
            writer.write("ID: " + actNutzer.getUserID() + "\n");
            writer.write("## Books\n");
            writer.write("| Title | Author | ISBN | Ausleihdatum |\n");
            writer.write("| ---- | ------- | ----- | ----- |\n");
            for (AusgeliehenesBuch book : actNutzer.getBorrowedBooks()) {
                writer.write("| " + book.getTitle() + " | " + book.getAuthor() +
                        " | " + book.getISBN() + " | " +
                        (book.getAusleihDatum() + " |\n"));
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
