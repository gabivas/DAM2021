package ro.ase.grupa1086.seminar8;

public class CursValutar {

    private String dateConcatenate;

    public CursValutar() {
    }

    public CursValutar(String dateConcatenate) {
        this.dateConcatenate = dateConcatenate;
    }

    public String getDateConcatenate() {
        return dateConcatenate;
    }

    public void setDateConcatenate(String dateConcatenate) {
        this.dateConcatenate = dateConcatenate;
    }

    @Override
    public String toString() {
        return "CursValutar{" +
                "dateConcatenate='" + dateConcatenate + '\'' +
                '}';
    }
}
