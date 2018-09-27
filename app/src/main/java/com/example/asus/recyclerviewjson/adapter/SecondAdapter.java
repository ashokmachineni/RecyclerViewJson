package com.example.asus.recyclerviewjson.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.recyclerviewjson.R;
import com.example.asus.recyclerviewjson.model.Rvdata;

import java.util.ArrayList;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.SecViewHolder> {

    Context scontext;
    ArrayList<Rvdata> rvdatas;

    public SecondAdapter(Context scontext, ArrayList<Rvdata> rvdatas) {
        this.scontext = scontext;
        this.rvdatas = rvdatas;
    }

    @Override
    public SecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.secondlist,parent,false);
        SecViewHolder secViewHolder = new SecViewHolder(view);
        return secViewHolder;
    }

    @Override
    public void onBindViewHolder(SecViewHolder holder, int position) {
        final Rvdata rvdata = rvdatas.get(position);
        holder.secTxt.setText(rvdata.getTitle());
        String imgsurl = rvdata.getThumbnail();
        Glide.with(scontext)
                .load(imgsurl)
                .thumbnail(0.5f)
                .into(holder.secImg);

    }

    @Override
    public int getItemCount() {
        return rvdatas.size();
    }

    public class SecViewHolder extends RecyclerView.ViewHolder {
        TextView secTxt;
        ImageView secImg;
        LinearLayout sls;
        public SecViewHolder(View itemView) {
            super(itemView);
            secTxt = itemView.findViewById(R.id.article_sname);
            secImg = itemView.findViewById(R.id.item_simg);
            sls = itemView.findViewById(R.id.ll_sitem);

        }
    }



}
