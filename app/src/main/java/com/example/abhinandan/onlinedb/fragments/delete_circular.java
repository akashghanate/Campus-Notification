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
import android.widget.ListView;

import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Background_retrieve;
import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Teacherlayout;


public class delete_circular extends android.support.v4.app.Fragment {

    public static ListView lvcirculars;
    View views;
    Context context;
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_delete_circular,container,false);
        context = container.getContext();
        lvcirculars = views.findViewById(R.id.lvdelete);

        sp = context.getSharedPreferences("MY_SHARE",Context.MODE_PRIVATE);
        String usn = sp.getString("UserID","");
        String type = "del_retrieve";
        Background_retrieve br = new Background_retrieve(context);
        br.execute(type,usn);

        return views;
    }

    @Override
    public void onResume() {
        ((Teacherlayout) getActivity())
                .setActionBarTitle("Delete Circulars");
        super.onResume();
    }
}
