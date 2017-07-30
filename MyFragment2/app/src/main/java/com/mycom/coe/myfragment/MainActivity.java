package com.mycom.coe.myfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBlankFragment();
    }

    private void startBlankFragment() {
        BlankFragment blankFragment = (BlankFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment );
        TextView tvf1 =  (TextView) blankFragment.getView().findViewById(R.id.tvf1);
        tvf1.setText("Blank fragment");
    }
}
