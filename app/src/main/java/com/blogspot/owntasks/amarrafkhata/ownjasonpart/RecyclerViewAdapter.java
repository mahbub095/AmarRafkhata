package com.blogspot.owntasks.amarrafkhata.ownjasonpart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.blogspot.owntasks.amarrafkhata.R;
import com.blogspot.owntasks.amarrafkhata.model.Listmodel;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Listmodel> dataAdapters;
    public RecyclerViewAdapter(List<Listmodel> getDataAdapter, Context context) {
        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylistview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final String title = dataAdapters.get(position).getTitle();
        final String detail = dataAdapters.get(position).getDetail();
        final String time = dataAdapters.get(position).getTime();
        viewHolder.TextViewName.setText(title);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(title, detail, time);
                Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataAdapters.size();
    }
    private void openDetailActivity(String title, String detail, String date) {
        Intent i = new Intent(context, Details.class);

        //PACK DATA TO SEND
        i.putExtra("TITLE", title);
        i.putExtra("DETAIL", detail);
        i.putExtra("DATE", date);
        //open activity
         context.startActivity(i);

    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView TextViewName;
        ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            TextViewName = (TextView) itemView.findViewById(R.id.textviewforlist);
            itemView.setOnClickListener((View.OnClickListener) this);

        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }
}
