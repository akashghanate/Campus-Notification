package com.example.abhinandan.onlinedb.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Studentlayout;

public class about_student extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Studentlayout) getActivity())
                .setActionBarTitle("About Us");
    }
}
