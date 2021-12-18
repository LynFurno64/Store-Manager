package com.example.storemanager.ui.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storemanager.NavActivity;
import com.example.storemanager.R;
import com.example.storemanager.database.Meal;
import com.example.storemanager.database.MenuLab;
import com.example.storemanager.databinding.FragmentShowFoodDetailsBinding;

import java.util.UUID;

public class ShowFoodDetailsFragment extends Fragment {
    private static final String ARG_MEAL_ID = "meal_id";

    private Meal mMeal;
    private int amount;
    private int totalBrought;
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
            mMeal = MenuLab.get(getActivity()).getMeal(dishId);
            totalBrought = mMeal.getTimesOrder(); // Gets the total number of that sold
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
                totalBrought += amount;
                mMeal.setTimesOrder(totalBrought);
                Toast.makeText(getActivity(),"Total sold: " + mMeal.getTimesOrder(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.nav_show_delete, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_meal:
                AlertDialog.Builder delete = new AlertDialog.Builder(getContext());
                delete.setTitle(R.string.app_name);
                delete.setMessage("Do you want to delete the meal?");
                delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        MenuLab.get(getActivity()).deleteMeal(mMeal);
                        getActivity().onBackPressed();
                    }
                });
                delete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDelete = delete.create();
                alertDelete.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MenuLab.get(getActivity()).updateCrime(mMeal);
    }

    // To prevent memory leak
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((NavActivity)getActivity()).setDrawer_unlock();// UnLock Drawer
        binding = null;
    }
}