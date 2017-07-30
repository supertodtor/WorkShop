package com.mycom.coe.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    final String APP = "APP_PREFERENCES";

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSharedPreferences();

        readSharedPreferences();
    }

    private void readSharedPreferences() {
        String str = sp.getString("KEY", "Default String");
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        File f = getDatabasePath(APP + ".xml");
        System.out.println(f.getAbsolutePath());
    }

    private void createSharedPreferences() {
        sp = getSharedPreferences(APP, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putString("KEY", "Foo Bar");
        editor.putBoolean("LOGIC", true);
        editor.commit();
    }

}
