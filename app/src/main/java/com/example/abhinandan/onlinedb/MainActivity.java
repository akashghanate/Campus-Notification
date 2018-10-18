package com.example.abhinandan.onlinedb;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Backgroundworker;

public class MainActivity extends AppCompatActivity {
    EditText Usn,Passwordet;
    RadioGroup rg;
    RadioButton rb1,rb2;
    int flag = 0;
    SharedPreferences sharedPreferences;
    String author = "Student";
    AlertDialog alertDialog;
    static public String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usn = (EditText)findViewById(R.id.etUsername);
        Passwordet = (EditText)findViewById(R.id.etPassword);
        rb1 = (RadioButton)findViewById(R.id.radiostudent);
        rb2 = (RadioButton)findViewById(R.id.radiostaff);
        rg = (RadioGroup)findViewById(R.id.radiogroup);

        sharedPreferences = getSharedPreferences("MY_SHARE",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("IsLogged",false)){
            if(sharedPreferences.getBoolean("IsStudent",false)){
                startActivity(new Intent(this,Studentlayout.class));
                MainActivity.this.finish();
            }else{
                startActivity(new Intent(this,Teacherlayout.class));
                MainActivity.this.finish();
            }
        }

    }


    public void onlogin(View view){
        String usn = Usn.getText().toString();
        String password = Passwordet.getText().toString();
        name = usn;
        String type = "login";
        if(rg.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Please choose an option",Toast.LENGTH_SHORT).show();
        }else {
            Backgroundworker bw = new Backgroundworker(this, flag);
            bw.execute(type, usn, password, author);
            Toast.makeText(this,"Welcome User",Toast.LENGTH_SHORT).show();
        }
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
                    author = "Student";
                }
                break;
            case R.id.radiostaff:
                if(checked){
                    flag = 1;
                    author = "Teacher";
                }
                break;
        }
    }

}
