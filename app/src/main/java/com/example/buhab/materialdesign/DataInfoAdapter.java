package com.example.buhab.materialdesign;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class DataInfoAdapter extends RecyclerView.Adapter<DataInfoAdapter.DataInfoViewHolder> {

    private LayoutInflater inflater;
    List<DataInfo> data = Collections.emptyList();

    public DataInfoAdapter(Context context, List<DataInfo> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public DataInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.data_info_row, parent, false);
        DataInfoViewHolder dataInfoHolder = new DataInfoViewHolder(view);
        return dataInfoHolder;
    }

    @Override
    public void onBindViewHolder(DataInfoViewHolder holder, int position) {
        DataInfo current = data.get(position);
        holder.image.setImageResource(current.iconId);
        holder.title.setText(current.title);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DataInfoViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public DataInfoViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageDataInfo);
            title = (TextView) itemView.findViewById(R.id.titleDataInfo);
        }
    }
}
