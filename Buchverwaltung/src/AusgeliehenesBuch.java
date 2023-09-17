import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AusgeliehenesBuch extends Buch{

    private LocalDate ausleihDatum;
    private LocalDate rueckgabeDatum;

    public AusgeliehenesBuch(Buch buch) {
        super(buch.getISBN(), buch.getTitle(), buch.getAuthor(), false);
        this.ausleihDatum = LocalDate.now();
        this.rueckgabeDatum = this.ausleihDatum.plusWeeks(2);
    }


    public LocalDate getAusleihDatum() {
        return ausleihDatum;
    }

    public void setAusleihDatum(LocalDate ausleihDatum) {
        this.ausleihDatum = ausleihDatum;
    }

    public LocalDate getRueckgabeDatum() {
        return rueckgabeDatum;
    }

    public void setRueckgabeDatum(LocalDate rueckgabeDatum) {
        this.rueckgabeDatum = rueckgabeDatum;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "AusgeliehenesBuch{" +
                "ausleihDatum=" + ausleihDatum.format(formatter) +
                ", rueckgabeDatum=" + rueckgabeDatum.format(formatter) +
                '}';
    }

}
