package com.example.storemanager.ui.add_meals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.storemanager.R;
import com.example.storemanager.database.Meal;
import com.example.storemanager.databinding.FragmentAddMealBinding;
import com.example.storemanager.databinding.FragmentOrderBinding;

public class AddMealFragment extends Fragment {
    FragmentAddMealBinding binding;
    private Meal newMeal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newMeal = new Meal();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMealBinding.inflate(getLayoutInflater());

        //Button Click Saves meal
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Meal is: "+ binding.dishName.getText() + " and " + binding.price.getText();
                Toast.makeText(getActivity(),"New Meal: " + message,Toast.LENGTH_LONG).show();
                newMeal.setName(String.valueOf(binding.dishName.getText())); // Meal Name
                newMeal.setPrice(Double.parseDouble(String.valueOf(binding.price.getText()))); // Meal Price
            }
        });
        return binding.getRoot();
    }
}
