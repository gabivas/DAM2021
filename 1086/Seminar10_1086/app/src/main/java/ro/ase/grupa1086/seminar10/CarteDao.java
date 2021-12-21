package ro.ase.grupa1086.seminar10;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarteDao {

    @Insert
    long insert(Carte carte);

    @Query("select * from carte")
    List<Carte> getAll();

    @Query("UPDATE carte SET titlu =:titlu WHERE id= :id")
    int updateCarte(int id,String titlu);

    @Delete
    void delete(Carte carte);

    @Query("delete from carte")
    void deleteAll();
}
