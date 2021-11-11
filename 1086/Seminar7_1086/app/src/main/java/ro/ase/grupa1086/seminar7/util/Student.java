package ro.ase.grupa1086.seminar7.util;

import java.io.Serializable;

public class Student implements Serializable {

    private String nume;
    private long numarIdentificare;
    private int lunaInscriereFacultate;
    private int anInscriereFacultate;
    private String numeFacultate;

    public Student(String nume, long numarIdentificare, int lunaInscriereFacultate, int anInscriereFacultate, String numeFacultate) {
        this.nume = nume;
        this.numarIdentificare = numarIdentificare;
        this.lunaInscriereFacultate = lunaInscriereFacultate;
        this.anInscriereFacultate = anInscriereFacultate;
        this.numeFacultate = numeFacultate;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public long getNumarIdentificare() {
        return numarIdentificare;
    }

    public void setNumarIdentificare(long numarIdentificare) {
        this.numarIdentificare = numarIdentificare;
    }

    public int getLunaInscriereFacultate() {
        return lunaInscriereFacultate;
    }

    public void setLunaInscriereFacultate(int lunaInscriereFacultate) {
        this.lunaInscriereFacultate = lunaInscriereFacultate;
    }

    public int getAnInscriereFacultate() {
        return anInscriereFacultate;
    }

    public void setAnInscriereFacultate(int anInscriereFacultate) {
        this.anInscriereFacultate = anInscriereFacultate;
    }

    public String getNumeFacultate() {
        return numeFacultate;
    }

    public void setNumeFacultate(String numeFacultate) {
        this.numeFacultate = numeFacultate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", numarIdentificare=" + numarIdentificare +
                ", lunaInscriereFacultate=" + lunaInscriereFacultate +
                ", anInscriereFacultate=" + anInscriereFacultate +
                ", numeFacultate='" + numeFacultate + '\'' +
                '}';
    }
}
