public class Buch {


    private int ISBN;
    private String title;
    private String author;
    private boolean free;

    public Buch() {
    }

    public Buch(int ISBN, String title, String author, boolean free) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.free = free;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "Buch{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", free=" + free +
                '}';
    }
}
