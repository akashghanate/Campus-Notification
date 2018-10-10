package com.example.abhinandan.onlinedb;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.adapters.Quick_msg_adapter;
import com.example.abhinandan.onlinedb.models.QuickmsgModel;

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

public class Background_quick extends AsyncTask<String,Void,List<QuickmsgModel>> {
    Context context;
    AlertDialog alertDialog;
    Background_quick(Context ctx){
        context = ctx;
    }
    @Override
    protected List<QuickmsgModel> doInBackground(String... voids) {
        String type = voids[0];
        String retrieve_url = "http://circularmanagement.000webhostapp.com/retrieve.php";
        if(type.equals("retrieve")){
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
                StringBuffer finallist = new StringBuffer();
                JSONObject parentobject = new JSONObject(finaljson);
                JSONArray parentarray = parentobject.getJSONArray("details");
                List<QuickmsgModel> quickmsglist = new ArrayList<>();
                for(int i=0;i<parentarray.length();i++) {
                    JSONObject childobject = parentarray.getJSONObject(i);
                    QuickmsgModel model = new QuickmsgModel();
                    model.setUser_ID(childobject.getString("User_ID"));
                    model.setmessage(childobject.getString("Message"));
                    model.setSemester1(childobject.getString("Semester"));
                    model.setBranch1(childobject.getString("Branch"));
                    model.setSection1(childobject.getString("Section"));
                    model.setDate1(childobject.getString("Date"));
                    quickmsglist.add(model);
                }
                br.close();
                is.close();
                htc.disconnect();
                return quickmsglist;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<QuickmsgModel> quickmsgModels) {
        super.onPostExecute(quickmsgModels);
        Quick_msg_adapter adapter = new Quick_msg_adapter(context,R.layout.row_quickmessage,quickmsgModels);
        Student_quick_msg.lvmessages.setAdapter(adapter);
        Student_quick_msg.lvmessages.setDivider(context.getDrawable(R.drawable.divider));
    }
}
