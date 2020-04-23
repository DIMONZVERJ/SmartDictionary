package roomdatabase;

import android.app.Application;
import android.arch.persistence.room.Room;

public class AppDelegate extends Application {
    private DictionaryDatabase database;

    @Override
    public void onCreate()
    {
        super.onCreate();
        database = Room.databaseBuilder(this, DictionaryDatabase.class, "dictionary")
                .allowMainThreadQueries()
                .build();
    }
    public DictionaryDatabase getAppDatabase()
    {
        return database;
    }
}