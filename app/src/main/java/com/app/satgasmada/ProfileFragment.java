package com.app.satgasmada;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class ProfileFragment extends Fragment {

    View viewProfile;
    private Button mButton;
    private Button logout;
    private FirebaseFirestore db;
    private String name;
    private String email;
    private Map<String,Object> usersdata;
    private FirebaseAuth mAuth;
    TextView username_;
    TextView username_2;
    TextView makun;

    public ProfileFragment() {
        // Required empty public constructor
    }
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();

        db = FirebaseFirestore.getInstance();
        getdata();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);

        username_=(TextView) viewProfile.findViewById(R.id.username);

        username_2=(TextView) viewProfile.findViewById(R.id.username2);
        makun=(TextView) viewProfile.findViewById(R.id.akun) ;

        logout= (Button) viewProfile.findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLogout();
            }
        });

        mButton = (Button) viewProfile.findViewById(R.id.changeLanguageBtn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });


        return viewProfile;
    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"English", "Bahasa Indonesia"};
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(viewProfile.getContext());
        mBuilder.setTitle("Change Language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    setLocale("en");
                    getActivity().recreate();
                }
                else if (i == 1) {
                    setLocale("id");
                    getActivity().recreate();
                }

                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale= locale;
        getActivity().getBaseContext().getResources().updateConfiguration(
                configuration, getActivity().getBaseContext().getResources().getDisplayMetrics()
        );
        SharedPreferences.Editor editor = getActivity().
                getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("My Language",lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My Language","");
        setLocale(language);
    }

    private void getdata() {

        DocumentReference docRef = db.collection("users").document("4nmxIXXnoMTxZwDV7e3rkupa4ok2");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        usersdata =document.getData();

                        Log.d(TAG, "DocumentSnapshot data: " + document.getData().getClass().getSimpleName());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void getLogout() {
        FirebaseAuth.getInstance().signOut();

    }
}