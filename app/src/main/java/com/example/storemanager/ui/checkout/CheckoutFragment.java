package com.example.storemanager.ui.checkout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storemanager.R;
import com.example.storemanager.databinding.FragmentCheckoutBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentCheckoutBinding binding;

    private String mParam1;
    private String mParam2;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    public static CheckoutFragment newInstance(String param1, String param2) {
        CheckoutFragment fragment = new CheckoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCheckoutBinding.inflate(getLayoutInflater());

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}