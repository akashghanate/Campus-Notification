package com.example.abhinandan.onlinedb.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Background_db;
import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Teacherlayout;


public class student_database extends android.support.v4.app.Fragment implements View.OnClickListener{
    Spinner s1,s2,s3;
    Button b1;
    SharedPreferences sp;
    View views;
    static public ListView lvstudentlists;
    Context context;
    static public AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_student_database,container,false);
        context = container.getContext();
        s1 = (Spinner)views.findViewById(R.id.sd_sem);
        s2 = (Spinner)views.findViewById(R.id.sd_branch);
        s3 = (Spinner)views.findViewById(R.id.sd_section);
        b1 = (Button)views.findViewById(R.id.getdb);
        lvstudentlists = (ListView)views.findViewById(R.id.lvstudentlist);

        b1.setOnClickListener(this);

        ArrayAdapter<String> myada = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Semester));
        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(myada);

        ArrayAdapter<String> myada1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Branch));
        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(myada1);

        ArrayAdapter<String> myada2 = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Section));
        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(myada2);

        lvstudentlists.setEmptyView(views.findViewById(R.id.empty));

        return views;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.getdb:
                String type = "list";
                String sel_sem = s1.getSelectedItem().toString();
                String sel_branch = s2.getSelectedItem().toString();
                String sel_sec = s3.getSelectedItem().toString();
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Status");
                alertDialog.setCancelable(false);
                alertDialog.setMessage("Fetching Students List....");
                alertDialog.show();
                Background_db bdb = new Background_db(context);
                bdb.execute(type,sel_sem,sel_branch,sel_sec);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((Teacherlayout)getActivity())
                .setActionBarTitle("Student Database");
    }
}