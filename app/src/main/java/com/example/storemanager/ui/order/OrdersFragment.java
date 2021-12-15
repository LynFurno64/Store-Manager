package com.example.storemanager.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storemanager.R;
import com.example.storemanager.database.Meal;
import com.example.storemanager.database.MenuLab;
import com.example.storemanager.databinding.FragmentOrderBinding;

import java.util.List;

public class OrdersFragment extends Fragment {
    FragmentOrderBinding binding;
    private RecyclerView mMenuRecyclerView;
    private DishAdapter mAdapter;
    private List<Meal> mMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());

        mMenuRecyclerView = binding.rvMenuList.findViewById(R.id.rv_menu_List);
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Open Menu List
        binding.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment menuListFrg = new MenuListFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_nav, menuListFrg);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    private void updateUI() {
        MenuLab menuLab = MenuLab.get(getActivity());
        List<Meal> dishes = menuLab.getMenu();
        mAdapter = new DishAdapter(dishes);
        mMenuRecyclerView.setAdapter(mAdapter);
    }// updateUI

    // To prevent memory leak
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //---------------stores and recycles views as they are scrolled off screen--------------------\\
    //--------------------------------------------------------------------------------------------\\
    private class MealsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        public MealsHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cardview_menu_row, parent, false));

            myTextView = itemView.findViewById(R.id.tvMealsPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }// MealsHolder


    //--------------------------------------------------------------------------------------------\\
    //--------------------------------------------------------------------------------------------\\
    private class DishAdapter extends RecyclerView.Adapter<MealsHolder> {
        public DishAdapter(List<Meal> meals) {
            mMenu = meals;
        }

        // inflates the row layout from xml when needed
        @Override
        public MealsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MealsHolder(layoutInflater, parent);
        }

        // binds the data into TexView for each row
        @Override
        public void onBindViewHolder(MealsHolder holder, int position) {
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
