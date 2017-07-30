package com.mycom.coe.persistent;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mycom.coe.persistent.model.Board;
import com.mycom.coe.persistent.until.DbHelper;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String APP = "APP_PREFERENCES";

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    SQLiteDatabase mDb;
    DbHelper mHelper;
    Cursor mCursor;

    ArrayList<Board> boards;
    ListView listView;
    private EditText editTitle;
    private EditText editName;
    private EditText editMessage;
    private Button btnSubmit;
    private TextView showText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //createSharedPreferences();

        //readSharedPreferences();



        boards = new ArrayList<Board>();
        mHelper = new DbHelper(this);
        mDb = mHelper.getWritableDatabase();

        input();

        //Board b1 =  mHelper.getMessage(1);  // get a a message
        //Toast.makeText(this, b1.toString(), Toast.LENGTH_SHORT).show();

        //b1.setMessage("Android is very easy");
        //mHelper.updateMessage(b1);                         // update a message

        //Board b2 =  mHelper.getMessage(1);  // get a a message
        //Toast.makeText(this, b2.toString(), Toast.LENGTH_SHORT).show();

        //mHelper.addMessage(new Board("New title","New Name","New Message"));

        // get all messages

        //showText = (TextView) findViewById(R.id.showText);
        boards = mHelper.getAllMessages();
        for (Board board : boards) {
            //Toast.makeText(this, board.toString(), Toast.LENGTH_SHORT).show();

            //showText.setText(board.toString());
        }



    }

    private void input() {
        editTitle = (EditText) findViewById(R.id.editTitle);
        editName = (EditText) findViewById(R.id.editName);
        editMessage = (EditText) findViewById(R.id.editMessage);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHelper.addMessage(new Board(editTitle.getText().toString()
                        ,editName.getText().toString()
                        ,editMessage.getText().toString()));
            }
        });
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
