package ro.ase.grupa1086.seminar12;

import java.io.Serializable;

public class Cadou implements Serializable {

    private String id;
    private String denumire;
    private String dimensiune;

    public Cadou() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(String dimensiune) {
        this.dimensiune = dimensiune;
    }

    @Override
    public String toString() {
        return "Cadou -" + denumire + dimensiune;
    }
}
