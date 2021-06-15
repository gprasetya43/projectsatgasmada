package com.app.satgasmada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportHolder> {
    //private LayoutInflater layoutInflater;

    Context mContext;
    List<ReportModel> mData;

    public ReportAdapter(Context mContext, List<ReportModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ReportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewReport;
        viewReport = LayoutInflater.from(mContext).inflate(R.layout.row_report,parent,false);
        ReportHolder viewReportHolder = new ReportHolder(viewReport);

        //layoutInflater=LayoutInflater.from(parent.getContext());
        //View view = layoutInflater.inflate(R.layout.row_report, parent,false);

        return viewReportHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportHolder holder, int position) {

        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_desc.setText(mData.get(position).getDesc());
        holder.tv_date.setText(mData.get(position).getDate());
        holder.tv_sender.setText(mData.get(position).getSender());
//        holder.img.setImageResource(mData.get(position).getImg());
        /**
        holder.mTitle.setText(ReportModel.getTitle());
        holder.mDesc.setText(ReportModel.getDesc());
        holder.mDate.setText(ReportModel.getDate());
        holder.mSender.setText(ReportModel.getSender());
        holder.mImageView.setImageResource(ReportModel.getImg());

        ReportHolder.mTitle.setText(ReportModel.get(position).getTitle());
        ReportHolder.mDesc.setText(ReportModel.get(position).getDesc());
        ReportHolder.mDate.setText(ReportModel.get(position).getDate());
        ReportHolder.mSender.setText(ReportModel.get(position).getSender());
        ReportHolder.mImageView.setImageResource(ReportModel.get(position).getImg());
         */

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static  class ReportHolder extends RecyclerView.ViewHolder{

        private TextView tv_title;
        private TextView tv_desc;
        private TextView tv_date;
        private TextView tv_sender;
        private ImageView img;

        public ReportHolder(View itemView){
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.itemTitle);
            tv_desc = (TextView) itemView.findViewById(R.id.itemDesc);
            tv_date = (TextView) itemView.findViewById(R.id.itemDate);
            tv_sender = (TextView) itemView.findViewById(R.id.itemSender);
            img = (ImageView) itemView.findViewById(R.id.itemImage);

        }
    }

}
