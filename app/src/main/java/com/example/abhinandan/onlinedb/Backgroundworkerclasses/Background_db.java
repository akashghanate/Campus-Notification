package com.example.abhinandan.onlinedb.Backgroundworkerclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.adapters.Student_db_adapter;
import com.example.abhinandan.onlinedb.models.StudentdbModel;

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
import com.example.abhinandan.onlinedb.fragments.student_database;

public class Background_db extends AsyncTask<String,Void,List<StudentdbModel>> {
    Context context;

    public Background_db(Context ctx){
        this.context = ctx;
    }

    @Override
    protected List<StudentdbModel> doInBackground(String... voids) {
        String type = voids[0];
        String db_url = "http://circularmanagement.000webhostapp.com/db_list.php";
        if(type.equals("list")) {
            try {
                String sem = voids[1];
                String branch = voids[2];
                String sec = voids[3];
                URL url = new URL(db_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("sem", "UTF-8") + "=" + URLEncoder.encode(sem, "UTF-8") + "&"
                        + URLEncoder.encode("branch", "UTF-8") + "=" + URLEncoder.encode(branch, "UTF-8") + "&"
                        + URLEncoder.encode("sec", "UTF-8") + "=" + URLEncoder.encode(sec, "UTF-8");
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
                List<StudentdbModel> studentdbModelArrayList = new ArrayList<>();
                for (int i = 0; i < parentarray.length(); i++) {
                    JSONObject childobject = parentarray.getJSONObject(i);
                    StudentdbModel model = new StudentdbModel();
                    model.setStudent_usn(childobject.getString("USN"));
                    model.setStudent_name(childobject.getString("Name"));
                    studentdbModelArrayList.add(model);
                }
                br.close();
                is.close();
                htc.disconnect();
                return studentdbModelArrayList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPostExecute(List<StudentdbModel> studentdbModels) {
        super.onPostExecute(studentdbModels);
        Student_db_adapter adapter = new Student_db_adapter(context,R.layout.row_studentslist,studentdbModels);
        student_database.alertDialog.dismiss();
        student_database.lvstudentlists.setAdapter(adapter);
    }
}
