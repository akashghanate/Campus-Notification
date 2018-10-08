package com.example.abhinandan.onlinedb.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.models.QuickmsgModel;
import java.util.List;

public class Quick_msg_adapter extends ArrayAdapter {
    public List<QuickmsgModel> messagelist;
    public LayoutInflater inflater;
    int resource;
    Context ctx;
    public Quick_msg_adapter( @NonNull Context context, int resource, @NonNull List<QuickmsgModel> objects) {
        super(context, resource, objects);
        ctx = context;
        messagelist = objects;
        this.resource = resource;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = inflater.inflate(resource,null);
        }
        TextView tvuser;
        TextView tvmessage;
        TextView tvsemester;
        TextView tvbranch;
        TextView tvsection;
        tvuser = (TextView)convertView.findViewById(R.id.textView_user);
        tvmessage = (TextView)convertView.findViewById(R.id.textView_message);
        tvsemester = (TextView)convertView.findViewById(R.id.textView_semester);
        tvbranch = (TextView)convertView.findViewById(R.id.textView_branch);
        tvsection = (TextView)convertView.findViewById(R.id.textView_section);

        tvuser.setText(messagelist.get(position).getUser_ID());
        tvmessage.setText(messagelist.get(position).getmessage());
        tvsemester.setText("Semester :" + messagelist.get(position).getSemester1());
        tvbranch.setText("Branch :" + messagelist.get(position).getBranch1());
        tvsection.setText("Section :" + messagelist.get(position).getSection1());

        return convertView;
    }
}
