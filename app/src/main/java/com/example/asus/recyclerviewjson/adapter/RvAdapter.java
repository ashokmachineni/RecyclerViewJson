package com.example.asus.recyclerviewjson.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.recyclerviewjson.NewActivity;
import com.example.asus.recyclerviewjson.R;
import com.example.asus.recyclerviewjson.model.Rvdata;

import java.util.ArrayList;

/**
 * Created by  techsolpoint.com on 3/18/2018.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder>{

    Context context;
    ArrayList<Rvdata> rvdatas;

    public RvAdapter(Context context,ArrayList<Rvdata> rvdatas){
        this.context = context;
        this.rvdatas = rvdatas;
    }

    View view;
    @Override
    public RvAdapter.RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        RvViewHolder rvViewHolder = new RvViewHolder(view);
        return rvViewHolder;
    }

    @Override
    public void onBindViewHolder(RvAdapter.RvViewHolder holder, int position) {
        final Rvdata  rvdata = rvdatas.get(position);
        holder.itemName.setText(rvdata.getTitle());
        String imgUrl = rvdata.getThumbnail();
        Glide.with(context)
                .load(imgUrl)
                .thumbnail(0.5f)
                .into(holder.itemImg);
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rvdatas.size();
    }

    public class RvViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        ImageView itemImg;
        LinearLayout llItem;
        public RvViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.article_name);
            itemImg = itemView.findViewById(R.id.item_img);
            llItem = itemView.findViewById(R.id.ll_item);
        }
    }
}

