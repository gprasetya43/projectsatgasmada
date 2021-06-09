package com.app.satgasmada;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ReportAddFragment extends Fragment {

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
        return view;
    }
}