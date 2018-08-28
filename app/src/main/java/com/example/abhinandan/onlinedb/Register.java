package com.example.abhinandan.onlinedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText newuser,newpass,confirmpass;
    String user_name,user_pass,user_con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        newuser = (EditText)findViewById(R.id.etnewuser);
        newpass = (EditText)findViewById(R.id.etnewpass);
        confirmpass = (EditText)findViewById(R.id.etconfirmpass);
    }

    public void onregister(View view){
        user_name = newuser.getText().toString();
        user_pass = newpass.getText().toString();
        user_con = confirmpass.getText().toString();
        String type = "register";
        Backgroundworker bw = new Backgroundworker(this);
        bw.execute(type,user_name,user_pass,user_con);
    }
}
