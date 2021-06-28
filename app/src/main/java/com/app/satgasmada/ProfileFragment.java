package com.app.satgasmada;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;


public class ProfileFragment extends Fragment  implements LocationListener {


    View viewProfile;
    private Button mButton;
    private Button logout;
    private FirebaseFirestore db;
    private String name;
    private String email;
    private Map<String, Object> usersdata;
    private FirebaseAuth mAuth;
    TextView username_;
    TextView username_2;
    TextView makun;
    TextView txt_loc;
    TextView locate;
    LocationManager locationManager;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Profile testProfile;


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

        username_ = (TextView) viewProfile.findViewById(R.id.username);
        username_2 = (TextView) viewProfile.findViewById(R.id.username2);
        makun = (TextView) viewProfile.findViewById(R.id.akun);

        txt_loc=(TextView) viewProfile.findViewById(R.id.Location_);
        locate=(TextView) viewProfile.findViewById(R.id.getlocate);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }




        getLocation_();


        logout= (Button) viewProfile.findViewById(R.id.logoutBtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(getActivity(),MainActivity.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();

        Log.d(TAG, "onCreateView: " + uid);

        DocumentReference docRef = db.collection("users").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        usersdata = document.getData();
                        String value = document.getString("name");
                        username_.setText(value);
                        username_2.setText(value);

                        Log.d(TAG, "onComplete: cek name " + value);
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData().getClass().getSimpleName());
                        String email = document.getString("email");
                        makun.setText(email);

                        Profile profile = document.toObject(Profile.class);

                        testProfile = profile;



                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }





//    private void setLogout(){
////        FirebaseAuth.getInstance().signOut();
//    }
    @SuppressLint("MissingPermission")
    private void getLocation_() {

        try {
            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,ProfileFragment.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
//        Toast.makeText(getContext(), ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            String uid = firebaseUser.getUid();

            if (testProfile != null) {
                testProfile.setLast_latitude(String.valueOf(location.getLatitude()));
                testProfile.setLast_longitude(String.valueOf(location.getLongitude()));

                update(uid, testProfile);

            }


            txt_loc.setText(address);
            Log.d(TAG, "onLocationChanged: " + address);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    public void update(String userId, Profile profile){
        db.collection("users").document(userId)
                .update(hashMapProfile(profile))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) Log.d("TAG", "Document was updated");
                        else Log.w("TAG", "Error updating document", task.getException());
                    }
                });
    }



    private Map<String, Object> hashMapProfile(Profile profile){
        Map<String, Object> document = new HashMap<>();
        document.put("avatar", profile.getAvatar());
        document.put("email", profile.getEmail());
        document.put("fcm_token", profile.getFcm_token());
        document.put("isActive", profile.isActive());
        document.put("last_latitude", profile.getLast_latitude());
        document.put("last_location_datetime", profile.getLast_location_datetime());
        document.put("last_login", profile.getLast_login());
        document.put("last_longitude", profile.last_longitude);
        document.put("name", profile.getName());
        document.put("password", profile.getPassword());
        document.put("role", profile.getRole());
        return document;
    }
}