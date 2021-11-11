package ro.ase.grupa1088.seminar7.util;

import java.io.Serializable;

public class Film implements Serializable {

    private String titlu;
    private String genFilm;
    private int lunaAparitie;
    private int anAparitie;
    private long id;
    private String regizor;

    public Film(String titlu, String genFilm, int lunaAparitie, int anAparitie, long id, String regizor) {
        this.titlu = titlu;
        this.genFilm = genFilm;
        this.lunaAparitie = lunaAparitie;
        this.anAparitie = anAparitie;
        this.id = id;
        this.regizor = regizor;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getGenFilm() {
        return genFilm;
    }

    public void setGenFilm(String genFilm) {
        this.genFilm = genFilm;
    }

    public int getLunaAparitie() {
        return lunaAparitie;
    }

    public void setLunaAparitie(int lunaAparitie) {
        this.lunaAparitie = lunaAparitie;
    }

    public int getAnAparitie() {
        return anAparitie;
    }

    public void setAnAparitie(int anAparitie) {
        this.anAparitie = anAparitie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegizor() {
        return regizor;
    }

    public void setRegizor(String regizor) {
        this.regizor = regizor;
    }

    @Override
    public String toString() {
        return "Film{" +
                "titlu='" + titlu + '\'' +
                ", genFilm='" + genFilm + '\'' +
                ", lunaAparitie=" + lunaAparitie +
                ", anAparitie=" + anAparitie +
                ", id=" + id +
                ", regizor='" + regizor + '\'' +
                '}';
    }
}
