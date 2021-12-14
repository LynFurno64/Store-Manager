package com.example.storemanager.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storemanager.R;
import com.example.storemanager.database.Meal;
import com.example.storemanager.database.MenuLab;

import java.util.ArrayList;
import java.util.List;

public class MenuListFragment extends Fragment implements MenuAdapter.ItemClickListener {

    private MenuAdapter mAdapter;
    private RecyclerView mMenuRecyclerView;
    private MealAdapter mAdapters;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cardview_menu_list, container, false);

        // set up the RecyclerView
        mMenuRecyclerView =view.findViewById(R.id.rvFoods);
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }// onItemClick

    private void updateUI() {
        MenuLab menuLab = MenuLab.get(getActivity());
        List<Meal> dishes = menuLab.getMenu();
        mAdapters = new MealAdapter(dishes);
        mMenuRecyclerView.setAdapter(mAdapters);
    }// updateUI


    // stores and recycles views as they are scrolled off screen
    private class FoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        public FoodHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cardview_menu_row, parent, false));

            myTextView = itemView.findViewById(R.id.tvMealsPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    private class MealAdapter extends RecyclerView.Adapter<FoodHolder> {

        private List<Meal> mMenu;

        public MealAdapter(List<Meal> meals) {
            mMenu = meals;
        }

        // inflates the row layout from xml when needed
        @Override
        public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new FoodHolder(layoutInflater, parent);
        }

        // binds the data into TexView for each row
        @Override
        public void onBindViewHolder(FoodHolder holder, int position) {
            Meal meals = mMenu.get(position);
            String mes = meals.getMeal() + " and " + meals.getPrice();
            holder.myTextView.setText(mes);
        }

        @Override
        public int getItemCount() {
            return mMenu.size();
        }
    }// MealAdapter
}