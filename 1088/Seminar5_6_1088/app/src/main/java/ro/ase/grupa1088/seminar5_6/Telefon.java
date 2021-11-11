package ro.ase.grupa1088.seminar5_6;

import java.io.Serializable;
import java.util.Date;
enum Retea{TREIG,PATRUG,CINCIG}
enum Culoare{ARGINTIU, ROSU, NEGRU}

public class Telefon implements Serializable {
    private String brand;
    private String model;
    private int memorie;
    private Date dataAparitie;
    private Retea retea;
    private Culoare culoare;

    public Telefon(String brand, String model, int memorie, Date dataAparitie, Retea retea, Culoare culoare) {
        this.brand = brand;
        this.model = model;
        this.memorie = memorie;
        this.dataAparitie = dataAparitie;
        this.retea = retea;
        this.culoare = culoare;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMemorie() {
        return memorie;
    }

    public void setMemorie(int memorie) {
        this.memorie = memorie;
    }

    public Date getDataAparitie() {
        return dataAparitie;
    }

    public void setDataAparitie(Date dataAparitie) {
        this.dataAparitie = dataAparitie;
    }

    public Retea getRetea() {
        return retea;
    }

    public void setRetea(Retea retea) {
        this.retea = retea;
    }

    public Culoare getCuloare() {
        return culoare;
    }

    public void setCuloare(Culoare culoare) {
        this.culoare = culoare;
    }

    @Override
    public String toString() {
        return "Telefon{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", memorie=" + memorie +
                ", dataAparitie=" + dataAparitie +
                ", retea=" + retea +
                ", culoare=" + culoare +
                '}';
    }
}
