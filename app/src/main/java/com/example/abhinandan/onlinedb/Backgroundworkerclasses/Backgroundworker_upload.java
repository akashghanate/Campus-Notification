package com.example.abhinandan.onlinedb.Backgroundworkerclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.abhinandan.onlinedb.fragments.add_circular;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Backgroundworker_upload extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;

    public Backgroundworker_upload(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        String upload_url = "http://circularmanagement.000webhostapp.com/upload_mongo.php";
        if (type.equals("upload")) {
            try {
                String userid = voids[1];
                String image = voids[2];
                String title = voids[3];
                String semeseter = voids[4];
                String branch = voids[5];
                String section = voids[6];
                long millis=System.currentTimeMillis();
                java.sql.Date date=new java.sql.Date(millis);
                URL url = new URL(upload_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8") + "&"
                        + URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(image, "UTF-8") + "&"
                        + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8") + "&"
                        + URLEncoder.encode("semester", "UTF-8") + "=" + URLEncoder.encode(semeseter, "UTF-8") + "&"
                        + URLEncoder.encode("branch", "UTF-8") + "=" + URLEncoder.encode(branch, "UTF-8") + "&"
                        + URLEncoder.encode("section", "UTF-8") + "=" + URLEncoder.encode(section, "UTF-8") + "&"
                        + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date.toString(), "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    result += line;
                }
                br.close();
                is.close();
                htc.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        add_circular.imageView.setImageResource(0);
        add_circular.et.setText("");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Upload Successful");
        alertDialog.show();
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Uploading....");
        alertDialog.show();
    }
}
