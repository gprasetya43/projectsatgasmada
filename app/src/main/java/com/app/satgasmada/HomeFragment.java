 package com.app.satgasmada;

 import android.os.Bundle;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import androidx.annotation.NonNull;
 import androidx.fragment.app.Fragment;
 import androidx.fragment.app.FragmentTransaction;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.google.android.gms.tasks.OnCompleteListener;
 import com.google.android.gms.tasks.Task;
 import com.google.android.material.floatingactionbutton.FloatingActionButton;
 import com.google.firebase.Timestamp;
 import com.google.firebase.firestore.CollectionReference;
 import com.google.firebase.firestore.DocumentReference;
 import com.google.firebase.firestore.FirebaseFirestore;
 import com.google.firebase.firestore.QueryDocumentSnapshot;
 import com.google.firebase.firestore.QuerySnapshot;

 import org.jetbrains.annotations.NotNull;

 import java.util.ArrayList;
 import java.util.List;

 public class HomeFragment extends Fragment {

     View viewReport;
     private FloatingActionButton mActionButton;
     private RecyclerView mRecyclerView;
     private List<com.app.satgasmada.ReportModel> listReport;
     private FirebaseFirestore database = FirebaseFirestore.getInstance();


     public HomeFragment() {
         // Required empty public constructor
     }

     public static HomeFragment newInstance() {
         HomeFragment fragment = new HomeFragment();
         return fragment;
     }

     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);



     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

         viewReport = inflater.inflate(R.layout.fragment_home, container, false);

         mRecyclerView = (RecyclerView) viewReport.findViewById(R.id.recyclerViewReport);
         mActionButton = (FloatingActionButton) viewReport.findViewById(R.id.btn_float);

         mActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View viewReport) {
                 FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                 fragmentTransaction.replace(R.id.fragment_container,new com.app.satgasmada.ReportAddFragment());
                 fragmentTransaction.commit();
             }
         });

        getdata();

         return viewReport;
     }

     private void getdata() {
         CollectionReference docRef = database.collection("reports");

         docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
             @Override
             public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                 if (task.isSuccessful()) {
                     List<String> list = new ArrayList<>();
                     List<ReportModel> listNotif = new ArrayList<>();

                     for (QueryDocumentSnapshot document : task.getResult()) {
                         list.add(document.getId());
                         String title = document.getString("title");
                         Timestamp createdAt = document.getTimestamp("createdAt");
                         String content = document.getString("description");
//                         List<String> image =  (List<String>) document.get("images");
                         DocumentReference docRef = document.getDocumentReference("userId");


                         Report report = new Report(
                                 docRef,
                                 null,
                                 content,
                                 null,
                                 "",
                                 "",
                                 title
                                );

                         ReportModel reportModel = new ReportModel(title,content, createdAt, "SendBy:User", 0);
//                                 String.valueOf(createdAt)
                         listNotif.add(reportModel);
                         Log.d("listNotif", listNotif.toString());
                         ReportAdapter notifAdapter = new ReportAdapter(getContext(), listNotif);
                         mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                         mRecyclerView.setAdapter(notifAdapter);
                     }

                 } else {
                     Log.d("Test", "Error getting documents: ", task.getException());
                 }
             }
         });

     }




 }