package com.app.satgasmada;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReportHolder extends RecyclerView.ViewHolder{

    ImageView mImageView;
    TextView mTitle, mDesc, mDate, mSender ;


    public ReportHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.itemImage);
        this.mTitle = itemView.findViewById(R.id.itemTitle);
        this.mDesc = itemView.findViewById(R.id.itemDesc);
        this.mDate = itemView.findViewById(R.id.itemDate);
        this.mSender = itemView.findViewById(R.id.itemSender);
    }
}
