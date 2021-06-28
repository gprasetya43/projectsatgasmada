package com.app.satgasmada;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    View viewNotif;
    private RecyclerView nRecyclerView;
    private List<com.app.satgasmada.NotifModel> listNotif;
    private FirebaseFirestore db;


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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewNotif = inflater.inflate(R.layout.fragment_notification, container, false);
        nRecyclerView = (RecyclerView) viewNotif.findViewById(R.id.recyclerViewNotif);

//        com.app.satgasmada.NotifAdapter notifAdapter = new com.app.satgasmada.NotifAdapter(getContext(), listNotif);
//        nRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        nRecyclerView.setAdapter(notifAdapter);
        db = FirebaseFirestore.getInstance();
        getdata();

        return viewNotif;
    }

    private void getdata() {
        CollectionReference docRef = db.collection("announcements");

        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    List<NotifModel> listNotif = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                        String title = document.getString("title");
                        Timestamp createdAt =  document.getTimestamp("createdAt");
                        String content = document.getString("content");
                        NotifModel notifModel = new NotifModel(title, content,createdAt,0);
                        listNotif.add(notifModel);
                    }
                    Log.d("listNotif", listNotif.toString());
                    NotifAdapter notifAdapter = new com.app.satgasmada.NotifAdapter(getContext(), listNotif);
                    nRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    nRecyclerView.setAdapter(notifAdapter);
                } else {
                    Log.d("Test", "Error getting documents: ", task.getException());
                }
            }
        });

    }


}