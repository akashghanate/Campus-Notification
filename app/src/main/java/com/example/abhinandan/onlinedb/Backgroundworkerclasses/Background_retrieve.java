package com.example.abhinandan.onlinedb.Backgroundworkerclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.adapters.Delete_circluar_adapter;
import com.example.abhinandan.onlinedb.models.DeleteCircularModel;

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
import com.example.abhinandan.onlinedb.fragments.delete_circular;

public class Background_retrieve extends AsyncTask<String,Void,List<DeleteCircularModel>> {

    Context context;
    AlertDialog alertDialog;
    public Background_retrieve(Context ctx){
        this.context = ctx;
    }

    @Override
    protected List<DeleteCircularModel> doInBackground(String... voids) {
        String type = voids[0];
        String retrieve_url = "http://circularmanagement.000webhostapp.com/circular_del_list.php";
        if(type.equals("del_retrieve")){
            try {
                String user_id = voids[1];
                URL url = new URL(retrieve_url);
                HttpURLConnection htc = (HttpURLConnection)url.openConnection();
                if (htc==null){
                    Toast.makeText(context,"connection failed",Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String post_data = URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(user_id,"UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                StringBuffer result = new StringBuffer();
                String line = "";
                while((line = br.readLine())!=null){
                    result.append(line);
                }
                String finaljson = result.toString();
                JSONObject parentobject = new JSONObject(finaljson);
                JSONArray parentarray = parentobject.getJSONArray("details");
                List<DeleteCircularModel> cirdellist = new ArrayList<>();
                for(int i=0;i<parentarray.length();i++) {
                    JSONObject childobject = parentarray.getJSONObject(i);
                    DeleteCircularModel model = new DeleteCircularModel();
                    model.setCirulartitle(childobject.getString("Title"));
                    model.setDate_of_post(childobject.getString("Date"));
                    cirdellist.add(model);
                }
                br.close();
                is.close();
                htc.disconnect();
                return cirdellist;
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
        alertDialog.setMessage("Fetching Circulars List....");
        alertDialog.show();
    }

    @Override
    protected void onPostExecute(List<DeleteCircularModel> deleteCircularModels) {
        super.onPostExecute(deleteCircularModels);
        alertDialog.dismiss();
        Delete_circluar_adapter adapter = new Delete_circluar_adapter(context,R.layout.row_deletecircular,deleteCircularModels);
        delete_circular.lvcirculars.setAdapter(adapter);
    }
}
