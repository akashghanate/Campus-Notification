package com.example.abhinandan.onlinedb.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.abhinandan.onlinedb.AdminLayout;
import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Background_count;
import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Background_db;
import com.example.abhinandan.onlinedb.R;

import java.sql.Array;


public class reports extends android.support.v4.app.Fragment implements View.OnClickListener {
    Spinner s1;
    static public TextView t1;
    Button b1;
    int flag = 1;
    View views;
    SharedPreferences sp;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_reports,container,false);
        s1 = (Spinner)views.findViewById(R.id.spinner_report);
        t1 = (TextView)views.findViewById(R.id.nocirculars);
        b1 = (Button)views.findViewById(R.id.buttongetreport);
        b1.setOnClickListener(this);
        context = container.getContext();


        ArrayAdapter<String> myada = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Staff));
        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(myada);

        return views;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttongetreport:
                String type = "count";
                String usn = s1.getSelectedItem().toString();
                Background_count bcount = new Background_count(context);
                bcount.execute(type,usn);
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AdminLayout)getActivity()).setActionBarTitle("Reports");
    }
}
