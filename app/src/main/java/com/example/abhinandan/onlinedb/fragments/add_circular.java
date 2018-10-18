package com.example.abhinandan.onlinedb.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.Backgroundworkerclasses.Backgroundworker_upload;
import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Teacherlayout;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


public class add_circular extends android.support.v4.app.Fragment implements View.OnClickListener {
    ImageView imageView;
    EditText et;
    String encodedimage;
    SharedPreferences sp;
    Button b1,b2,b3;
    TextView tv1;
    Spinner s1,s2,s3;
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
        b3 = (Button)views.findViewById(R.id.upload);
        et = (EditText)views.findViewById(R.id.ettitle);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        s1 = (Spinner)views.findViewById(R.id.chosen_sem);
        s2 = (Spinner)views.findViewById(R.id.chosen_branch);
        s3 = (Spinner)views.findViewById(R.id.chosen_section);
        context  = container.getContext();

        ArrayAdapter<String> myada = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Semester));
        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(myada);

        ArrayAdapter<String> myada1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Branch));
        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(myada1);

        ArrayAdapter<String> myada2 = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Section));
        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(myada2);


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
            case R.id.upload:
                Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                encodedimage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
                uploadthefile();
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

    public void uploadthefile(){
        sp = context.getSharedPreferences("MY_SHARE",Context.MODE_PRIVATE);
        String userid = sp.getString("UserID","");
        String type = "upload";
        String title = et.getText().toString();
        String image = encodedimage;
        String semester = s1.getSelectedItem().toString();
        String branch = s2.getSelectedItem().toString();
        String section = s3.getSelectedItem().toString();
        Backgroundworker_upload bu = new Backgroundworker_upload(context);
        bu.execute(type,userid,image,title,semester,branch,section);
    }
}
