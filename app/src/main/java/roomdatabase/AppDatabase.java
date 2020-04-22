package roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Dictionary.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DictionaryDao DictionaryDao();
}
