package com.app.satgasmada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.NotifHolder> {

    Context nContext;
    List<NotifModel> nData;

    public NotifAdapter(Context nContext, List<NotifModel> nData) {
        this.nContext = nContext;
        this.nData = nData;
    }

    @NonNull
    @Override
    public NotifHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewNotif;
        viewNotif = LayoutInflater.from(nContext).inflate(R.layout.row_notif,parent,false);
        NotifHolder viewNotifHolder = new NotifHolder(viewNotif);

        return viewNotifHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotifHolder holder, int position) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String date = dateFormat.format(nData.get(position).getDateNotif().toDate());

        holder.tv_titleN.setText(nData.get(position).getTitleNotif());
        holder.tv_descN.setText(nData.get(position).getDescNotif());
        holder.tv_dateN.setText(date);
//        holder.imgN.setImageResource(nData.get(position).getImgNotif());
    }

    @Override
    public int getItemCount() {
        return nData.size();
    }

    public static class NotifHolder extends RecyclerView.ViewHolder{
        private TextView tv_titleN;
        private TextView tv_descN;
        private TextView tv_dateN;
        private ImageView imgN;

        public NotifHolder (View itemNotifView){
            super(itemNotifView);

            tv_titleN = (TextView) itemNotifView.findViewById(R.id.notifTitle);
            tv_descN = (TextView) itemNotifView.findViewById(R.id.notifDesc);
            tv_dateN = (TextView) itemNotifView.findViewById(R.id.notifDate);
            imgN = (ImageView) itemNotifView.findViewById(R.id.notifImage);
        }
    }
}
