package roomdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DictionaryDao{

    @Query("SELECT * FROM Dictionary order by id asc")
    List<Dictionary> getAll();

    @Query("SELECT * FROM Dictionary WHERE id = :id")
    Dictionary getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dictionary dictionary);

    @Update
    void update(Dictionary dictionary);

    @Delete
    void delete(Dictionary dictionary);

    @Query("DELETE FROM Dictionary where id = :id")
    void deleteById(int id);
}


