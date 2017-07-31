package com.mycom.coe.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mycom.coe.retrofitexample.GitHubServicess.GitHubService;
import com.mycom.coe.retrofitexample.util.HttpManager;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GitHubUser gitHub = new GitHubUser();
    private TextView tvDisplay;
    private Button btnSubmit;
    private EditText edtName;
    private HttpManager httpManager;
    private GitHubService gitHubService;
    private GitHubUser gitHubUser;
    private ImageView imDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.edtName);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        imDisplay = (ImageView) findViewById(R.id.imDisplay);

        httpManager = HttpManager.getInstance();
        gitHubService = HttpManager.getService();

        Picasso.with(MainActivity.this)
                .load("https://assets-cdn.github.com/images/modules/logos_page/Octocat.png")
                .resize(1000, 800)
                .into(imDisplay);

        serVice();

    }

    private void serVice() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                Call<GitHubUser> call = gitHubService.loadUser(name);

                call.enqueue(new Callback<GitHubUser>() {
                    @Override
                    public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                        if (response.isSuccessful()) {
                            gitHubUser = response.body();
                            Toast.makeText(getApplicationContext(), gitHubUser.toString(), Toast.LENGTH_SHORT).show();
                            Picasso.with(MainActivity.this)
                                    .load(gitHubUser.getImage())
                                    .resize(800, 800)
                                    .into(imDisplay);
                            tvDisplay.setText(gitHubUser.toString());
                        } else {
                            try {
                                Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<GitHubUser> call, Throwable t) {

                    }
                });
                Toast.makeText(getApplicationContext(), "Hi..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
