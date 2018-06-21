package edu.cnm.deepdive.lracca.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private DatabaseHelper db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    db = new DatabaseHelper(this);

    Button newNoteButton = findViewById(R.id.new_note_button);

    final EditText newNoteText = findViewById(R.id.new_note_text);

    newNoteButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        db.addNote(newNoteText.getText().toString());
        refreshList();
      }
    });

    refreshList();
  }

  private void refreshList() {
  List<String> notes = db.getAllNotes();

    ListView listView = findViewById(R.id.notes_list);

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
        android.R.layout.simple_list_item_1, notes);

    listView.setAdapter(arrayAdapter);
  }
}
