package com.example.abhinandan.onlinedb.Backgroundworkerclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.fragments.details_student;
import com.example.abhinandan.onlinedb.fragments.details_staff;

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

public class Background_details extends AsyncTask<String,Void,JSONObject> {

    Context context;
    int flag_student = 0;
    AlertDialog alertDialog;

    public Background_details(Context ctx){
        context = ctx;
    }
    @Override
    protected JSONObject doInBackground(String... voids) {
        String type = voids[0];
        String details_student_url = "http://circularmanagement.000webhostapp.com/details_student.php";
        String details_staff_url = "http://circularmanagement.000webhostapp.com/details_staff.php";
        if (type.equals("details_stud")) {
            try {
                String user_id = voids[1];
                flag_student = 1;
                URL url = new URL(details_student_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
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
                JSONObject childobject = parentarray.getJSONObject(0);
                br.close();
                is.close();
                htc.disconnect();
                return childobject;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if(type.equals("details_staff")){
            try {
                String user_id = voids[1];
                flag_student = 0;
                URL url = new URL(details_staff_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
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
                JSONObject childobject = parentarray.getJSONObject(0);
                br.close();
                is.close();
                htc.disconnect();
                return childobject;
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
        alertDialog.setMessage("Fetching Details....");
        alertDialog.show();

    }

    @Override
    protected void onPostExecute(JSONObject childobject) {
        super.onPostExecute(childobject);
        alertDialog.dismiss();
        if(flag_student == 1) {
            try {
                details_student.et1.setText(childobject.getString("Name"));
                details_student.et2.setText(childobject.getString("USN"));
                details_student.et3.setText(childobject.getString("Semester"));
                details_student.et4.setText(childobject.getString("Branch"));
                details_student.et5.setText(childobject.getString("Section"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try{
                details_staff.tv1.setText(childobject.getString("Name"));
                details_staff.tv2.setText(childobject.getString("USN"));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
