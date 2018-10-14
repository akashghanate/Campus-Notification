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
import android.widget.ListView;
import android.widget.Toast;


public class Student_quick_msg extends android.support.v4.app.Fragment implements View.OnClickListener{

    static ListView lvmessages;
    View views;
    Button button1;
    Context context1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_student_quick_msg, container, false);
        lvmessages = views.findViewById(R.id.lvmessages);
        button1 = (Button)views.findViewById(R.id.button_getinfo);
        context1 = container.getContext();
        button1.setOnClickListener(this);
        String type = "retrieve";
        Background_quick bw = new Background_quick(context1);
        bw.execute(type,MainActivity.name);
        return views;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_getinfo:
                Toast.makeText(context1,"Getting Quick Messages",Toast.LENGTH_SHORT).show();
                String type = "retrieve";
                Background_quick bw = new Background_quick(context1);
                bw.execute(type,MainActivity.name);
                break;
        }

    }

    @Override
    public void onResume() {
        ((Studentlayout) getActivity())
                .setActionBarTitle("Quick Messages");
        super.onResume();
    }
}
