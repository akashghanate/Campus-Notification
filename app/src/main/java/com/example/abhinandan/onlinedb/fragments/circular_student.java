package com.example.abhinandan.onlinedb.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Background_download;
import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Studentlayout;
import com.example.abhinandan.onlinedb.adapters.circular_card_adapter;
import com.example.abhinandan.onlinedb.models.cardview_details;

import java.util.ArrayList;
import java.util.List;


public class circular_student extends android.support.v4.app.Fragment {
    static public RecyclerView recyclerView;
    View views;
    Context context;
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         views=inflater.inflate(R.layout.fragment_circular_student,container,false);
         recyclerView= views.findViewById(R.id.recyclerView);
         context = container.getContext();

         String type = "download";
         sp = context.getSharedPreferences("MY_SHARE",Context.MODE_PRIVATE);
         String usn = sp.getString("UserID","");
         Background_download bwd = new Background_download(context);
         bwd.execute(type,usn);
         return views;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Studentlayout) getActivity())
                .setActionBarTitle("Circulars");
    }

}
