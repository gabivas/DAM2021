package ro.ase.grupa1088.seminar10;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "melodii")
public class Melodie {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String denumire;
    private String artist;
    private String gen;
    private String durata;

    public Melodie(String denumire, String artist, String gen, String durata) {
        this.denumire = denumire;
        this.artist = artist;
        this.gen = gen;
        this.durata = durata;
    }

    @Ignore
    public Melodie() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return id +
                ",'" + denumire + '\'' +
                ",'" + artist + '\'' +
                "," + gen + '\'' +
                ",'" + durata;
    }
}
