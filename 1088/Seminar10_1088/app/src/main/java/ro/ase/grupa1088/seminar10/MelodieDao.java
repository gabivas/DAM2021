package ro.ase.grupa1088.seminar10;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MelodieDao {

    @Insert
    long insert(Melodie melodie);

    @Query("UPDATE melodii SET durata= :durata WHERE id= :id")
    int updateMelodie(int id, String durata);

    @Query("SELECT * from melodii")
    List<Melodie> getAll();

    @Query("DELETE from melodii")
    void deleteAll();

    @Delete
    void delete(Melodie melodie);
}
