package roomdatabase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Dictionary{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String word;

    private String translate;

    public Dictionary(int id) {
    }

    public Dictionary(int id, String word, String translate) {
        this.id = id;
        this.word = word;
        this.translate = translate;
    }

    public Dictionary() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}

