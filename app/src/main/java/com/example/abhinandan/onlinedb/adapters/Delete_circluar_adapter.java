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
import com.example.abhinandan.onlinedb.models.DeleteCircularModel;
import java.util.List;

public class Delete_circluar_adapter extends ArrayAdapter {
    public List<DeleteCircularModel> dcm;
    public LayoutInflater layoutInflater;
    int resource;
    Context ctx;

    public Delete_circluar_adapter(@NonNull Context context, int resource, @NonNull List<DeleteCircularModel> objects) {
        super(context, resource, objects);
        ctx = context;
        dcm = objects;
        this.resource = resource;
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(resource,null);
        }
        TextView circulartitle;
        TextView dateofposting;
        circulartitle = (TextView)convertView.findViewById(R.id.circulartitle);
        dateofposting = (TextView)convertView.findViewById(R.id.circulardate);

        circulartitle.setText("Title : " + dcm.get(position).getCirulartitle());
        dateofposting.setText("Date Of Posting : " + dcm.get(position).getDate_of_post());

        return convertView;
    }
}
