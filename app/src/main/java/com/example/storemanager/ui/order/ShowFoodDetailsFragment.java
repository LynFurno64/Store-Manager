package com.example.storemanager.ui.order;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.storemanager.NavActivity;
import com.example.storemanager.R;
import com.example.storemanager.database.Meal;
import com.example.storemanager.database.MenuLab;
import com.example.storemanager.databinding.FragmentOrderBinding;
import com.example.storemanager.databinding.FragmentShowFoodDetailsBinding;

import java.util.UUID;

public class ShowFoodDetailsFragment extends Fragment {
    private static final String ARG_MEAL_ID = "meal_id";

    private Meal mMeal;
    private int amount;
    FragmentShowFoodDetailsBinding binding;

    public ShowFoodDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShowFoodDetailsBinding.inflate(getLayoutInflater());
        amount = Integer.parseInt(String.valueOf(binding.textAmount.getText())); // Setting Amount

        // Retrieve Bundle
        Bundle bundle = this.getArguments();
        if(bundle != null){
            UUID dishId = UUID.fromString((String) bundle.get("meal_ID"));
            mMeal = MenuLab.get(getActivity()).getMeals(dishId);
        }
        binding.mealTitle.setText(mMeal.getName());
        binding.mealPrice.setText("$"+ mMeal.getPrice());

        ((NavActivity) getActivity()).setDrawer_lock();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // add another instance
        binding.plusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                amount += 1;
                binding.textAmount.setText(String.valueOf(amount));
            }
        });

        // remove an instance
        binding.minusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                amount -= 1;
                binding.textAmount.setText(String.valueOf(amount));
            }
        });

        binding.addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    // To prevent memory leak
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((NavActivity)getActivity()).setDrawer_unlock();// UnLock Drawer
        binding = null;
    }
}