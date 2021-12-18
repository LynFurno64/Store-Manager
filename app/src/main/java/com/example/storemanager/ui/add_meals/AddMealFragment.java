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
import com.example.storemanager.database.MenuLab;
import com.example.storemanager.databinding.FragmentAddMealBinding;
import com.example.storemanager.databinding.FragmentOrderBinding;

public class AddMealFragment extends Fragment {
    FragmentAddMealBinding binding;
    private Meal newMeal;
    private String dish;
    private String price;

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
                dish = String.valueOf(binding.dishName.getText());
                price = String.valueOf(binding.price.getText());
                if (dish.isEmpty() || price.isEmpty()){
                    Toast.makeText(getActivity(),"Complete Forum",Toast.LENGTH_LONG).show();
                }
                else{
                    newMeal.setName(dish); // Meal Name
                    newMeal.setPrice(Double.parseDouble(price)); // Meal Price
                    MenuLab.get(getActivity()).addMeal(newMeal);
                    Toast.makeText(getActivity(),"SAVED ",Toast.LENGTH_LONG).show();
                }
            }
        });
        return binding.getRoot();
    }
}
