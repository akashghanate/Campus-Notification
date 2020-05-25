package com.example.abhinandan.onlinedb.Backgroundworkerclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.fragments.reports;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Background_count extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;

    public Background_count(Context ctx){
        this.context = ctx;
    }
    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        String count_url = "http://circularmanagement.000webhostapp.com/count.php";
        if(type.equals("count")){
            try {
                String usn = voids[1];
                URL url = new URL(count_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("usn", "UTF-8") + "=" + URLEncoder.encode(usn, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = new String();
                String line = "";
                while ((line = br.readLine()) != null) {
                    result+=line;
                }
                br.close();
                is.close();
                htc.disconnect();
                return result;
            } catch (Exception e) {
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
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Fetching No of Circulars....");
        alertDialog.show();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        alertDialog.dismiss();
        reports.t1.setText("No of Posted Circulars = "+s);
    }
}
