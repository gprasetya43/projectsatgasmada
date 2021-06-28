package com.app.satgasmada;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportAddFragment extends Fragment {

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    EditText edtTitle, edtDescription ;
    Button changeLanguageBtn;
    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID=0;
    private static final String CHANNEL_ID="ch1";

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

        notificationManager=(NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
        createNotificationChannel();

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
                        Timestamp.now(),
                        edtDescription.getText().toString(),
                        images,
                        "0.0",
                        "0.0",
                        edtTitle.getText().toString()

                );

        insert( testReport);

//                Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
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
                        if (task.isSuccessful()){
                            Log.d("cek insert", "Document was added");
                            //TODO : showNotification()
                            showNotification();
                        }
                        else {
                            Log.w("cek insert", "Error adding document", task.getException());
                        }
                    }
                });
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Channel1";
            String desc="Description..";
            int importance= NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(desc);
            notificationManager.createNotificationChannel(channel);

        }
    }
    private void showNotification() {
        Log.d("cek notif", "notif add");
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                .setContentTitle("Satgasmada")
                .setContentText("Success Add Report")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


// notificationId is a unique int for each notification that you must define
//        notificationManager.notify(NOTIFICATION_ID, builder.build());
        Notification notification=builder.build();
        notificationManager.notify(NOTIFICATION_ID,notification);

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