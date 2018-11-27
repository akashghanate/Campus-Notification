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
    RadioButton rb1,rb2,rb3;
    int flag = 0;
    SharedPreferences sharedPreferences;
    String author = "Student";                                  //identify the login type
    static public String name = "";                             //used to set the username on successful login
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usn = (EditText)findViewById(R.id.etUsername);
        Passwordet = (EditText)findViewById(R.id.etPassword);
        rb1 = (RadioButton)findViewById(R.id.radiostudent);
        rb2 = (RadioButton)findViewById(R.id.radiostaff);
        rb3 = (RadioButton)findViewById(R.id.radioadmin);
        rg = (RadioGroup)findViewById(R.id.radiogroup);

        sharedPreferences = getSharedPreferences("MY_SHARE",MODE_PRIVATE);                 //This part is used to keep the
        if(sharedPreferences.getBoolean("IsLogged",false)){                                //user logged in. Redirects the
            if(sharedPreferences.getBoolean("IsStudent",false)){                           //to specified screen based on
                startActivity(new Intent(this,Studentlayout.class));               //login
                MainActivity.this.finish();
            }else{
                startActivity(new Intent(this,Teacherlayout.class));
                MainActivity.this.finish();
            }
        }

    }


    public void onlogin(View view){
        String usn = Usn.getText().toString();                                  //method executed when user presses login buttton
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
        startActivity(new Intent(this,Register.class));                     //when user presses signup login
    }

    public void checktheuser(View view){                                                //check whether atleast one of the
        boolean checked = ((RadioButton)view).isChecked();                              //radio buttons is selected
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
            case R.id.radioadmin:
                if(checked){
                    flag = 2;
                    author = "Admin";
                }
        }
    }

}
