package ro.ase.grupa1086.seminar12;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Laptop implements Serializable {

    private String id;
    private String marca;
    private String procesor;

    public Laptop() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProcesor() {
        return procesor;
    }

    public void setProcesor(String procesor) {
        this.procesor = procesor;
    }

    @NonNull
    @Override
    public String toString() {
        return "Laptop:" + marca + procesor;
    }
}
