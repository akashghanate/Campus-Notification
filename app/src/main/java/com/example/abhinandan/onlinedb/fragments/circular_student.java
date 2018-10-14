package com.example.abhinandan.onlinedb.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Studentlayout;


public class circular_student extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_circular_student,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Studentlayout) getActivity())
                .setActionBarTitle("Circulars");
    }

}
