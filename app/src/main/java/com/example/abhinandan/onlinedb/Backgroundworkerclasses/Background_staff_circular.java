package com.example.abhinandan.onlinedb.Backgroundworkerclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.Popupimage;
import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.adapters.circular_card_adapter;
import com.example.abhinandan.onlinedb.interfaces.onclickinterface;
import com.example.abhinandan.onlinedb.models.cardview_details;
import com.example.abhinandan.onlinedb.fragments.circular_staff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Background_staff_circular extends AsyncTask<String,Void,List<cardview_details>> {

    Context context;
    AlertDialog alertDialog;
    public Background_staff_circular(Context ctx){
        this.context = ctx;
    }
    @Override
    protected List<cardview_details> doInBackground(String... voids) {
        String type = voids[0];
        String download_url = "http://circularmanagement.000webhostapp.com/retrieve_circ_staff.php";
        if (type.equals("download_staff")) {
            try {
                URL url = new URL(download_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                String finaljson = result.toString();
                JSONObject parentobject = new JSONObject(finaljson);
                JSONArray parentarray = parentobject.getJSONArray("details");
                List<cardview_details> cvd = new ArrayList<>();
                for(int i=0;i<parentarray.length();i++) {
                    JSONObject childobject = parentarray.getJSONObject(i);
                    cardview_details card = new cardview_details();
                    card.setTeacher_name(childobject.getString("User_ID"));
                    card.setTime(childobject.getString("Title"));
                    String encoded = childobject.getString("Image");
                    byte[] decodedString = Base64.decode(encoded, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    card.setBackground(decodedByte);
                    card.setDate(childobject.getString("Date"));
                    cvd.add(card);
                }
                br.close();
                is.close();
                htc.disconnect();
                return cvd;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        alertDialog.setMessage("Fetching Circulars....");
        alertDialog.show();
    }

    @Override
    protected void onPostExecute(List<cardview_details> cardview_details) {
        super.onPostExecute(cardview_details);
        alertDialog.dismiss();
        circular_card_adapter adapter = new circular_card_adapter(context, cardview_details, new onclickinterface() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(context,"Position = "+position,Toast.LENGTH_SHORT).show();
                ImageView is = view.findViewById(R.id.card_background);
                Bitmap bitmap = ((BitmapDrawable)is.getDrawable()).getBitmap();
                Popupimage.bitmap = bitmap;
                context.startActivity(new Intent(context,Popupimage.class));
            }
        });
        circular_staff.recyclerView2.setAdapter(adapter);
        circular_staff.recyclerView2.setLayoutManager(new LinearLayoutManager(context));
    }
}
