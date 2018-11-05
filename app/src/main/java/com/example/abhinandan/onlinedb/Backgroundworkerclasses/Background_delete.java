package com.example.abhinandan.onlinedb.Backgroundworkerclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Background_delete extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sp;

    public Background_delete(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        String delete_url = "http://circularmanagement.000webhostapp.com/circular_delete.php";
        if (type.equals("delete")) {
            try {
                String title = voids[1];
                URL url = new URL(delete_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8");
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        alertDialog.setCancelable(true);
        if(s.equals("Success")){
            alertDialog.setMessage("Deletion Successful");
            alertDialog.show();
            sp = context.getSharedPreferences("MY_SHARE",Context.MODE_PRIVATE);
            String usn = sp.getString("UserID","");
            String type = "del_retrieve";
            Background_retrieve br = new Background_retrieve(context);
            br.execute(type,usn);
        }else{
            alertDialog.setMessage("Deletion Failed. Try Again");
            alertDialog.show();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Deleting Circular....");
        alertDialog.show();
    }
}
