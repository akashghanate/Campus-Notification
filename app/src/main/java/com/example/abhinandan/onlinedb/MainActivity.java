package com.example.abhinandan.onlinedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText Usernameet,Passwordet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Usernameet = (EditText)findViewById(R.id.etUsername);
        Passwordet = (EditText)findViewById(R.id.etPassword);
    }

    public void onlogin(View view){
        String username = Usernameet.getText().toString();
        String password = Passwordet.getText().toString();
        String type = "login";

        Backgroundworker bw = new Backgroundworker(this);
        bw.execute(type,username,password);
    }

    public void signup(View view){
        startActivity(new Intent(this,Register.class));
    }
}
