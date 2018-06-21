package edu.cnm.deepdive.lracca.sqliteexample;

import android.app.Application;
import com.facebook.stetho.Stetho;


public class NoteApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);

  }

}
