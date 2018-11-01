package com.example.abhinandan.onlinedb.adapters;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.models.cardview_details;
import com.example.abhinandan.onlinedb.interfaces.onclickinterface;

import java.util.List;

public class circular_card_adapter extends RecyclerView.Adapter<circular_card_adapter.myViewHolder> {
    Context mContecxt;
    List<cardview_details> mData;
    public LayoutInflater inflater;
    public onclickinterface mlist;


    public circular_card_adapter(Context mContecxt, List<cardview_details> mData,onclickinterface list) {
        this.mlist = list;
        this.mContecxt = mContecxt;
        this.mData = mData;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       inflater= LayoutInflater.from(mContecxt);
        View v = inflater.inflate(R.layout.circular_single_cardview,viewGroup,false);
        return new myViewHolder(v,mlist);
    }


    public void onBindViewHolder(myViewHolder holder, int i) {
        holder.background.setImageBitmap(mData.get(i).getBackground());
        holder.posting_date.setText(mData.get(i).getDate());
        holder.posting_time.setText(mData.get(i).getTime());
        holder.teacher.setText(mData.get(i).getTeacher_name());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView background;
        TextView teacher,posting_date,posting_time;
        public onclickinterface mlist;


        public myViewHolder(View itemView, onclickinterface list) {
            super(itemView);
            mlist = list;
            background=itemView.findViewById(R.id.card_background);
            teacher=itemView.findViewById(R.id.teacher_name_circular);
            posting_date=itemView.findViewById(R.id.date_circular);
            posting_time=itemView.findViewById(R.id.time_circular);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlist.onClick(view,getAdapterPosition());
        }
    }
}
