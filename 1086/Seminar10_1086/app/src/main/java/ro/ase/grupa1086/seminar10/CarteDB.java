package ro.ase.grupa1086.seminar10;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Carte.class}, version = 1,exportSchema = false)
public abstract class CarteDB extends RoomDatabase{

    private static final String DB_NAME="carti.db";
    private static CarteDB instanta;

    public static CarteDB getInstance(Context context){
        if(instanta==null){
            instanta= Room.databaseBuilder(context,CarteDB.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanta;
    }
    public abstract CarteDao getCarteDao();
}
