package edu.cnm.deepdive.lracca.sqliteexample;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

  public static  final String DB_NAME = "notes_db";
  public static final int DB_VERSION = 1;

  public  DatabaseHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }


  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("CREATE TABLE notes ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "note TEXT,"+
            "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP" +
            ")");
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS notes");

    //Re-Create
    onCreate(sqLiteDatabase);
  }

  public void addNote(String note) {
    SQLiteDatabase db = this.getWritableDatabase();

    db.execSQL("INSERT INTO notes ('note') VALUES ("+
        DatabaseUtils.sqlEscapeString(note) +")");

    db.close();
  }

  public List<String> getAllNotes() {
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.rawQuery("SELECT `note` FROM notes ORDER BY timestamp DESC", null);

    List<String> notes = new ArrayList<>();

    while(cursor.moveToNext()) {
      notes.add(cursor.getString(0));
    }

    db.close();

    return notes;
  }
}
