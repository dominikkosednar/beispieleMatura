import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Nutzer {

    private int userID;
    private String name;
    private List<AusgeliehenesBuch> borrowedBooks;

    public Nutzer(int userID, String name) {
        this.userID = userID;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AusgeliehenesBuch> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<AusgeliehenesBuch> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void addBook(AusgeliehenesBuch myBook){
        this.borrowedBooks.add(myBook);
    }

    public void removeBook(AusgeliehenesBuch myBook){
        this.borrowedBooks.remove(myBook);
    }


    public void leiheBuchAus(Buch buch) {
        if (buch.isFree()) {
            AusgeliehenesBuch myAusBook = new AusgeliehenesBuch(buch);
            buch.setFree(false);
            borrowedBooks.add(myAusBook);
        } else {
            System.out.println("Buch ist nicht frei");
        }
    }

    public void gibBuchZurueck(Buch buch){
        AusgeliehenesBuch zuEntfernendesBuch = null;
        for (AusgeliehenesBuch ausgeliehenesBuch : borrowedBooks) {
            if (ausgeliehenesBuch.getTitle() == buch.getTitle()) {
                zuEntfernendesBuch = ausgeliehenesBuch;
                break;
            }
        }
        if (zuEntfernendesBuch != null) {
            borrowedBooks.remove(zuEntfernendesBuch);
            buch.setFree(true);
            System.out.println("Buch erfolgreich zurückgegeben!");
        } else {
            System.out.println("Dieses Buch wurde nicht von diesem Nutzer ausgeliehen.");
        }
    }

    public int getAnzahlBuecher(){
        return borrowedBooks.size();
    }

    @Override
    public String toString() {
        return "Nutzer{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
