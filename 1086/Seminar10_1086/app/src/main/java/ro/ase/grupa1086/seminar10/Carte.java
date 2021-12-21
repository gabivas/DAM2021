package ro.ase.grupa1086.seminar10;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "carte")
public class Carte {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titlu;
    private String autor;
    private String editura;
    private String gen;
    private String dataLansare;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getDataLansare() {
        return dataLansare;
    }

    public void setDataLansare(String dataLansare) {
        this.dataLansare = dataLansare;
    }

    @Ignore
    public Carte() {}

    public Carte(String titlu, String autor, String editura, String gen, String dataLansare) {
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.gen = gen;
        this.dataLansare = dataLansare;
    }

    @Override
    public String toString() {
        return "" +
                 id +
                "," + titlu + '\'' +
                ",'" + autor + '\'' +
                ",'" + editura + '\'' +
                ",'" + gen + '\'' +
                ",'" + dataLansare + '\'' +
                '}';
    }
}
