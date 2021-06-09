package com.app.satgasmada;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.util.Locale;


public class ProfileFragment extends Fragment {

    View viewProfile;
    private Button mButton;
    private Button logout;


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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);


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


}