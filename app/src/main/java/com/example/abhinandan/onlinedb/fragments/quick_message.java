package com.example.abhinandan.onlinedb.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Backgroundworker;
import com.example.abhinandan.onlinedb.MainActivity;
import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Teacherlayout;


public class quick_message extends android.support.v4.app.Fragment implements View.OnClickListener {
    static public EditText text1;
    Spinner s1,s2,s3;
    Button but1;
    View views;
    SharedPreferences sp;
    String names;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_quick_message, container, false);
        but1 = (Button)views.findViewById(R.id.message_button);
        text1 = (EditText)views.findViewById(R.id.messagebox);
        s1 = (Spinner)views.findViewById(R.id.quick_sem);
        s2 = (Spinner)views.findViewById(R.id.branch_quick);
        s3 = (Spinner)views.findViewById(R.id.section_quick);

        but1.setOnClickListener(this);
        context = container.getContext();

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


        return views;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_button:
                String message = text1.getText().toString();
                String sel_sem = s1.getSelectedItem().toString();
                String sel_branch = s2.getSelectedItem().toString();
                String sel_sec = s3.getSelectedItem().toString();
                String type = "quick_message";
                sp = context.getSharedPreferences("MY_SHARE",Context.MODE_PRIVATE);
                names = sp.getString("UserID","");
                if(message.equals("")){
                    Toast.makeText(context,"Please type a message",Toast.LENGTH_SHORT).show();

                }else {
                    Backgroundworker bw = new Backgroundworker(context, 0);
                    bw.execute(type, names, message, sel_sem, sel_branch, sel_sec);
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Teacherlayout) getActivity())
                .setActionBarTitle("Quick Message");
    }
}
