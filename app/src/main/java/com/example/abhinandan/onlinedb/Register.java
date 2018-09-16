package com.example.abhinandan.onlinedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText newuser,newpass,confirmpass,newusn;
    Spinner spin,spin1,spin2;
    String user_name,user_pass,user_con,sem,branch,section,usn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        newuser = (EditText)findViewById(R.id.etnewuser);
        newpass = (EditText)findViewById(R.id.etnewpass);
        confirmpass = (EditText)findViewById(R.id.etconfirmpass);
        spin = (Spinner)findViewById(R.id.spinnersem);
        spin1 = (Spinner)findViewById(R.id.spinnerbranch);
        spin2 = (Spinner)findViewById(R.id.spinnersec);
        newusn = (EditText)findViewById(R.id.etusn);

        ArrayAdapter<String> myada = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Semester));
        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myada);

        ArrayAdapter<String> myada1 = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Branch));
        myada1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(myada1);

        ArrayAdapter<String> myada2 = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Section));
        myada2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(myada2);
    }

    public void onregister(View view){
        user_name = newuser.getText().toString();
        user_pass = newpass.getText().toString();
        user_con = confirmpass.getText().toString();
        sem = spin.getSelectedItem().toString();
        branch = spin1.getSelectedItem().toString();
        section = spin2.getSelectedItem().toString();
        usn = newusn.getText().toString();

        String type = "register";
        if(user_pass.equals(user_con)) {
            Backgroundworker bw = new Backgroundworker(this);
            bw.execute(type, user_name, user_pass, sem, branch, section, usn, user_con);
        }
        else{
            Toast.makeText(this,"The two Passwords do not match",Toast.LENGTH_SHORT).show();
        }
    }
}
