package com.example.storemanager.ui.statistics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.storemanager.R;
import com.example.storemanager.database.Statisticlab;
import com.example.storemanager.database.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StatisticFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String ARG_STAT_ID = "STAT_ID";

    private Statistics mStatistics;
    private double Total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //** this block of code is causing a crash**//
        //UUID id = (UUID) getArguments().getSerializable(ARG_STAT_ID);
        //mStatistics = Statisticlab.get(getActivity()).getStatistic(id);
    }

    @Override
    public void onPause(){
        super.onPause();
        Statisticlab.get(getActivity()).updateStat(mStatistics);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        // Spinner element
        Spinner spinner = view.findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("January");
        categories.add("February");
        categories.add("March");
        categories.add("April");
        categories.add("May");
        categories.add("June");
        categories.add("July");
        categories.add("August");
        categories.add("September");
        categories.add("November");
        categories.add("December");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        Button button = view.findViewById(R.id.button);
        TextView textView = view.findViewById(R.id.TextNumber);

        // Will display the profits when click
        button.setOnClickListener(v -> {
           Total =  mStatistics.getProfits();
            textView.setText("$"+Total);
        });

        return view;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
