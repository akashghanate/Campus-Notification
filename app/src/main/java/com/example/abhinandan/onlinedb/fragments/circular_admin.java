package com.example.abhinandan.onlinedb.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhinandan.onlinedb.AdminLayout;
import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Background_staff_circular;
import com.example.abhinandan.onlinedb.R;


public class circular_admin extends android.support.v4.app.Fragment {
    static public RecyclerView recyclerView3;
    View views;
    int flag = 1;
    Context context;
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_circular_staff,container,false);
        recyclerView3 = (RecyclerView)views.findViewById(R.id.recyclerView2);
        context = container.getContext();

        String type = "download_staff";
        sp = context.getSharedPreferences("MY_SHARE",Context.MODE_PRIVATE);
        String usn = sp.getString("UserID","");
        Background_staff_circular bsc = new Background_staff_circular(context,flag);
        bsc.execute(type,usn);
        return views;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AdminLayout) getActivity())
                .setActionBarTitle("Circulars");
    }


}
