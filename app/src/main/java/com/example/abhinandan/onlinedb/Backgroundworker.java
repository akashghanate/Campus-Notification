package com.example.abhinandan.onlinedb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.models.QuickmsgModel;

import org.json.JSONArray;
import org.json.JSONObject;

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

public class Backgroundworker extends AsyncTask<String,Void,String> {
    Context context;
    int status;
    AlertDialog alertDialog;
    public static class myclass{
        public static int flag = 0;
    }

    Backgroundworker (Context ctx,int flag){
        status = flag;
         context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        String login_url = "http://circularmanagement.000webhostapp.com/login.php";
        String register_url = "http://circularmanagement.000webhostapp.com/register.php";
        String message_url = "http://circularmanagement.000webhostapp.com/message.php";
        if (type.equals("login")){
            try {
                String usn = voids[1];
                String pass = voids[2];
                String author = voids[3];
                URL url = new URL(login_url);
                HttpURLConnection htc = (HttpURLConnection)url.openConnection();
                if (htc==null){
                    Toast.makeText(context,"connection failed",Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String post_data = URLEncoder.encode("usn","UTF-8")+"="+URLEncoder.encode(usn,"UTF-8")+"&"
                        +URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")+"&"
                        +URLEncoder.encode("author","UTF-8")+"="+URLEncoder.encode(author,"UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                String result = "";
                String line = "";
                while((line = br.readLine())!=null){
                    result+=line;
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
        else if(type.equals("register")){
            try {
                String username = voids[1];
                String password = voids[2];
                String semester = voids[3];
                String branch = voids[4];
                String section = voids[5];
                String usn = voids[6];
                URL url = new URL(register_url);
                HttpURLConnection htc = (HttpURLConnection)url.openConnection();
                if (htc==null){
                    Toast.makeText(context,"connection failed",Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode(semester,"UTF-8")+"&"
                        +URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"
                        +URLEncoder.encode("section","UTF-8")+"="+URLEncoder.encode(section,"UTF-8")+"&"
                        +URLEncoder.encode("usn","UTF-8")+"="+URLEncoder.encode(usn,"UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                String result = "";
                String line = "";
                while((line = br.readLine())!=null){
                    result+=line;
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
        else if(type.equals("quick_message")){
            try {
                String user_id = voids[1];
                String message = voids[2];
                String semester = voids[3];
                String branch = voids[4];
                String section = voids[5];
                URL url = new URL(message_url);
                HttpURLConnection htc = (HttpURLConnection)url.openConnection();
                if (htc==null){
                    Toast.makeText(context,"connection failed",Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String post_data = URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(user_id,"UTF-8")+"&"
                        +URLEncoder.encode("message","UTF-8")+"="+URLEncoder.encode(message,"UTF-8")+"&"
                        +URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode(semester,"UTF-8")+"&"
                        +URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"
                        +URLEncoder.encode("section","UTF-8")+"="+URLEncoder.encode(section,"UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                String result = "";
                String line = "";
                while((line = br.readLine())!=null){
                    result+=line;
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
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Login Successful") && status == 0){
            context.startActivity(new Intent(context,Studentlayout.class));
        }else if(result.equals("Login Successful") && status == 1){
            context.startActivity(new Intent(context,Teacherlayout.class));
        }else if(result.equals("message success")) {
            Toast.makeText(context,"Message has been posted",Toast.LENGTH_SHORT).show();
        }else{
                alertDialog.setMessage(result);
                alertDialog.show();
            }
        }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
