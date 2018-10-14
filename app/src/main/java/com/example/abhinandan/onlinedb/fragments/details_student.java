package com.example.abhinandan.onlinedb.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Background_details;
import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Studentlayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class details_student extends android.support.v4.app.Fragment {
    View view;
    static public TextView et1,et2,et3,et4,et5;
    Button button;
    Context context;
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_details_student,container,false);
       et1 = (TextView)view.findViewById(R.id.etnamestudent);
       et2 = (TextView)view.findViewById(R.id.etusnstudent);
       et3 = (TextView)view.findViewById(R.id.etsemstudent);
       et4 = (TextView)view.findViewById(R.id.etbranchstudent);
       et5 = (TextView)view.findViewById(R.id.etsectionstudent);
       context = container.getContext();


       String type = "details_stud";
       sp = context.getSharedPreferences("MY_SHARE",Context.MODE_PRIVATE);
       String usn = sp.getString("UserID","");
       Background_details bd = new Background_details(context);
       bd.execute(type,usn);

       et3.setMovementMethod(new ScrollingMovementMethod());

       return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Studentlayout) getActivity())
                .setActionBarTitle("Details");
    }

}
