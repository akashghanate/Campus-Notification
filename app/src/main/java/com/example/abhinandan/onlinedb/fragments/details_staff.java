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
import android.widget.TextView;

import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Background_details;
import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Teacherlayout;

import org.w3c.dom.Text;


public class details_staff extends android.support.v4.app.Fragment {
    View views;
    Context context;
    SharedPreferences sp;
    public static TextView tv1,tv2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_details_staff,container,false);
        context = container.getContext();
        tv1 = (TextView)views.findViewById(R.id.etnamestaff);
        tv2 = (TextView)views.findViewById(R.id.etusnstaff);

        sp = context.getSharedPreferences("MY_SHARE",Context.MODE_PRIVATE);
        String userid = sp.getString("UserID","");
        String type = "details_staff";
        Background_details bd = new Background_details(context);
        bd.execute(type,userid);
        return views;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((Teacherlayout)getActivity())
                .setActionBarTitle("Details");
    }
}
