package com.app.satgasmada;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    View viewNotif;
    private RecyclerView nRecyclerView;
    private List<com.app.satgasmada.NotifModel> listNotif;


    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listNotif = new ArrayList<>();
        listNotif.add(new com.app.satgasmada.NotifModel("Announcement 1","Meet at gathering point","March 15 2021  13:23:10",R.drawable.ic_round_new_releases_24));
        listNotif.add(new com.app.satgasmada.NotifModel("Announcement 2","Meet at GSP","March 24 2021  23:59:10",R.drawable.ic_round_new_releases_24));
        listNotif.add(new com.app.satgasmada.NotifModel("Announcement 3","Meet at Wisdom Park","March 28 2021  10:23:20",R.drawable.ic_round_new_releases_24));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewNotif = inflater.inflate(R.layout.fragment_notification, container, false);
        nRecyclerView = (RecyclerView) viewNotif.findViewById(R.id.recyclerViewNotif);

        com.app.satgasmada.NotifAdapter notifAdapter = new com.app.satgasmada.NotifAdapter(getContext(),listNotif);
        nRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        nRecyclerView.setAdapter(notifAdapter);

        return viewNotif;
    }
}