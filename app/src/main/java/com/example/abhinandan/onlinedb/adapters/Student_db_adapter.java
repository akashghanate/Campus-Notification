package com.example.abhinandan.onlinedb.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.models.StudentdbModel;

import java.io.Serializable;
import java.util.List;

public class Student_db_adapter extends ArrayAdapter {
    public List<StudentdbModel> sdm;
    public LayoutInflater layoutInflater;
    int resource;
    Context context;

    public Student_db_adapter(@NonNull Context context, int resource, @NonNull List<StudentdbModel> objects) {
        super(context, resource, objects);
        this.context = context;
        sdm = objects;
        this.resource = resource;
        layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(resource,null);
        }
        TextView studentusn;
        TextView studentname;

        studentusn = (TextView)convertView.findViewById(R.id.studentusn);
        studentname = (TextView)convertView.findViewById(R.id.studentname);

        studentusn.setText("USN : "+sdm.get(position).getStudent_usn());
        studentname.setText("Name : "+sdm.get(position).getStudent_name());

        return convertView;
    }
}
