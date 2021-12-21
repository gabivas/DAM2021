package ro.ase.grupa1088.seminar10;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Melodie.class}, version = 1, exportSchema = false)
public abstract class MelodieDB extends RoomDatabase{
    public static final String DB_NAME="melodii.db";
    private static MelodieDB instanta;

    public static MelodieDB getInstance(Context context){
        if(instanta==null){
            instanta= Room.databaseBuilder(context,MelodieDB.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanta;
    }
    public abstract MelodieDao getMelodieDao();

}
