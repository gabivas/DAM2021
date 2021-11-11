package ro.ase.grupa1086.seminar5_6;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
enum Motorizare{ BENZINA, DIESEL,ELECTRIC}
enum Culoare{ALB, NEGRU,ROSU};

public class Masina implements Serializable {
    private String marca;
    private String nrInmatriculare;
    private Date dataInmatriculare;
    private int anFabricatie;
    private Motorizare tipCombustibil;
    private Culoare culoareVehicul;

    public Masina(String marca, String nrInmatriculare, Date dataInmatriculare, int anFabricatie, Motorizare tipCombustibil, Culoare culoareVehicul) {
        this.marca = marca;
        this.nrInmatriculare = nrInmatriculare;
        this.dataInmatriculare = dataInmatriculare;
        this.anFabricatie = anFabricatie;
        this.tipCombustibil = tipCombustibil;
        this.culoareVehicul = culoareVehicul;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public Date getDataInmatriculare() {
        return dataInmatriculare;
    }

    public void setDataInmatriculare(Date dataInmatriculare) {
        this.dataInmatriculare = dataInmatriculare;
    }

    public int getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(int anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public Motorizare getTipCombustibil() {
        return tipCombustibil;
    }

    public void setTipCombustibil(Motorizare tipCombustibil) {
        this.tipCombustibil = tipCombustibil;
    }

    public Culoare getCuloareVehicul() {
        return culoareVehicul;
    }

    public void setCuloareVehicul(Culoare culoareVehicul) {
        this.culoareVehicul = culoareVehicul;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "marca='" + marca + '\'' +
                ", nrInmatriculare='" + nrInmatriculare + '\'' +
                ", dataInmatriculare=" + new SimpleDateFormat("dd/MM/yyyy").format(dataInmatriculare) +
                ", anFabricatie=" + anFabricatie +
                ", tipCombustibil=" + tipCombustibil +
                ", culoareVehicul=" + culoareVehicul +
                '}';
    }
}
