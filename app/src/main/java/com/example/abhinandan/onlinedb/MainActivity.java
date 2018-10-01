package com.example.abhinandan.onlinedb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Usernameet,Passwordet;
    RadioGroup rg;
    RadioButton rb1,rb2;
    int flag = 0;


    static String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar1);
        setSupportActionBar(toolbar);

        Usernameet = (EditText)findViewById(R.id.etUsername);
        Passwordet = (EditText)findViewById(R.id.etPassword);
        rb1 = (RadioButton)findViewById(R.id.radiostudent);
        rb2 = (RadioButton)findViewById(R.id.radiostaff);
        rg = (RadioGroup)findViewById(R.id.radiogroup);
    }


    public void onlogin(View view){
        Toast.makeText(this,"Login",Toast.LENGTH_SHORT).show();
        String username = Usernameet.getText().toString();
        String password = Passwordet.getText().toString();
        name = username;
        String type = "login";

        Backgroundworker bw = new Backgroundworker(this,flag);
        bw.execute(type,username,password);

    }

    public void signup(View view){
        startActivity(new Intent(this,Register.class));
    }

    public void checktheuser(View view){
        boolean checked = ((RadioButton)view).isChecked();
        switch(view.getId()){
            case R.id.radiostudent:
                if(checked){
                    flag = 0;
                }
                break;
            case R.id.radiostaff:
                if(checked){
                    flag = 1;
                }
                break;
        }
    }

}
