package com.example.abhinandan.onlinedb;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class quick_message extends android.support.v4.app.Fragment implements View.OnClickListener {
    EditText text1;
    Button but1;
    View views;
    int flag = 0;
    String names;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_quick_message, container, false);
        but1 = views.findViewById(R.id.message_button);
        text1 = (EditText)views.findViewById(R.id.messagebox);
        but1.setOnClickListener(this);
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_quick_message,container,false);
    }


    @Override
    public void onClick(View view) {
        String message = text1.getText().toString();
        String type = "quick_message";
        names = MainActivity.name;
        Backgroundworker bw = new Backgroundworker(context,flag);
        bw.execute(type,names,message);
    }
}
