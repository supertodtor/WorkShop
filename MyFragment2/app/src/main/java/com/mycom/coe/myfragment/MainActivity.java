package com.mycom.coe.myfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "JavaFragment";
    private Button btnAdd;
    private Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBlankFragment();

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRemove = (Button) findViewById(R.id.btnRemove);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JavaFragment javaFragment = new JavaFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.javaFragment,
                        javaFragment,
                        TAG);
                transaction.commit();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentByTag(TAG);
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(fragment)
                            .commit();
                }
            }
        });

//        startJavaFragment();
    }

    private void startJavaFragment() {
        JavaFragment javaFragment = new JavaFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.javaFragment, javaFragment);
        transaction.commit();
    }

    private void startBlankFragment() {
        BlankFragment blankFragment = (BlankFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        TextView tvf1 = (TextView) blankFragment.getView().findViewById(R.id.tvf1);
        tvf1.setText("Blank fragment");
    }
}
