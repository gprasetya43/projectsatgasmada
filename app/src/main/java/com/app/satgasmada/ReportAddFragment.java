package com.app.satgasmada;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportAddFragment extends Fragment {

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    EditText edtTitle, edtDescription ;
    Button changeLanguageBtn;

    public ReportAddFragment() {
        // Required empty public constructor
    }


    public static ReportAddFragment newInstance() {
        ReportAddFragment fragment = new ReportAddFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_report_add, container, false);



        edtTitle = view.findViewById(R.id.edtTitle);
        edtDescription = view.findViewById(R.id.edtDescReport);
        changeLanguageBtn = view.findViewById(R.id.changeLanguageBtn);

        changeLanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference docRef = database.collection("users").document("4nmxIXXnoMTxZwDV7e3rkupa4ok2");
                ArrayList<String> images = new ArrayList<>();
                images.add("list1");
                images.add("list2");
                Report testReport = new Report(
                        docRef,
                        new Timestamp(2021,06,20,21,34,12,0),
                        edtDescription.getText().toString(),
                        images,
                        "0.0",
                        "0.0",
                        edtTitle.getText().toString()

                );

        insert( testReport);

                Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void insert( Report report){
        database.collection("reports").document()
                .set(hashMapProfile(report))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) Log.d("cek insert", "Document was added");
                        else Log.w("cek insert", "Error adding document", task.getException());
                    }
                });
    }

    private Map<String, Object> hashMapProfile(Report profile){
        Map<String, Object> document = new HashMap<>();
        document.put("createdAt", profile.getCreatedAt());
        document.put("description", profile.getDescription());
        document.put("images", profile.getImages());
        document.put("latitude", profile.getLatitude());
        document.put("longitude", profile.getLongitude());
        document.put("title", profile.getTitle());
        document.put("userId", profile.getId());
        return document;
    }
}