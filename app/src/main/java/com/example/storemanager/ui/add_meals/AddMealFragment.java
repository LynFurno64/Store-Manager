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

public class AddMealFragment extends Fragment {
    FragmentAddMealBinding binding;
    private Meal newMeal;
    private Button mSendBtn;
    private EditText mPrice;
    private EditText mDishName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newMeal = new Meal();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_meal, container, false);

        mDishName = view.findViewById(R.id.dish_name);
        mPrice = view.findViewById(R.id.price);

        //Button Click Saves meal
        mSendBtn = view.findViewById(R.id.sendBtn);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String message = "Meal is: "+ mDishName.getText() + " and " + mPrice.getText();
                Toast.makeText(getActivity(),"New Meal: " + message,Toast.LENGTH_LONG).show();
                newMeal.setMeal(String.valueOf(mDishName.getText())); // Meal Name
                newMeal.setPrice(Double.parseDouble(String.valueOf(mPrice.getText()))); // Meal Price
            }
        });

        return view;
    }

    private void addMealBtn(){}//addMealBtn
}
