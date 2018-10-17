package com.example.abhinandan.onlinedb.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Teacherlayout;

import static android.app.Activity.RESULT_OK;


public class add_circular extends android.support.v4.app.Fragment implements View.OnClickListener {
    ImageView imageView;
    Button b1,b2;
    TextView tv1;
    View views;
    int imagesel = 0;
    int pdfsel = 0;
    Context context;
    Uri imageuri,pdfuri;
    private static final int PICK_IMAGE = 100;
    private static final int READ_PDF = 86;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_add_circular,container,false);
        imageView = (ImageView) views.findViewById(R.id.imagechoosefile);
        tv1 = (TextView)views.findViewById(R.id.tvchoosepdf);
        b1 = (Button)views.findViewById(R.id.choosefile);
        b2 = (Button)views.findViewById(R.id.choosepdf);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        context  = container.getContext();
        return views;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.choosefile:
                opengallery();
                break;
            case R.id.choosepdf:
                selectpdf();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Teacherlayout)getActivity()).setActionBarTitle("Add Circulars");
    }

    public void opengallery(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            if(pdfsel == 1){
                Toast.makeText(context,"Please select only one file",Toast.LENGTH_SHORT).show();
            }else {
                imageuri = data.getData();
                imageView.setImageURI(imageuri);
                imagesel = 1;
            }
        }
        if(resultCode == RESULT_OK && requestCode == READ_PDF){
            if(imagesel == 1){
                Toast.makeText(context,"Please select only one file",Toast.LENGTH_SHORT).show();
            }else {
                pdfuri = data.getData();
                tv1.setText(data.getData().getLastPathSegment());
                pdfsel = 1;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void selectpdf(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,READ_PDF);
    }
}
