package com.example.storemanager.ui.order;

import android.os.Bundle;
import android.util.Log;
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

import com.example.storemanager.NavActivity;
import com.example.storemanager.R;
import com.example.storemanager.database.Dish;
import com.example.storemanager.database.Meal;
import com.example.storemanager.database.MenuLab;
import com.example.storemanager.database.MonthlyBussinessReview;
import com.example.storemanager.databinding.FragmentOrderBinding;

import java.util.List;

public class OrdersFragment extends Fragment {
    private static final String TAG = "OrdersFragments";
    FragmentOrderBinding binding;
    private RecyclerView mMenuRecyclerView;
    private DishAdapter mAdapter;

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

    }

    private void updateUI() {
        MenuLab menuLab = MenuLab.get(getActivity());
        List<Meal> dishes = menuLab.getMenu();

        if (mAdapter == null) {
            mAdapter = new DishAdapter(dishes);
            mMenuRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }// updateUI

    // To prevent memory leak
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    //---------------stores and recycles views as they are scrolled off screen--------------------\\
    //--------------------------------------------------------------------------------------------\\
    private class MealsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Meal mMeal;
        private TextView mTextViewTitle;
        private TextView mTextViewPrice;

        public MealsHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cardview_menu_row, parent, false));
            itemView.setOnClickListener(this);
            mTextViewTitle = itemView.findViewById(R.id.tv_meal_title);
            mTextViewPrice = itemView.findViewById(R.id.tv_meal_price);
        }

        //This will be called each time a new Meal is displayed in your CrimeHolder
        //When given a Meal, MealHolder will now update the title TextView and price TextView to reflect the
        //state of the Meal.
        public void bind(Meal meal){
            mMeal = meal;
            mTextViewTitle.setText(mMeal.getName());
            mTextViewPrice.setText(String.valueOf(mMeal.getPrice()));
        }// bind

        @Override
        public void onClick(View view) {
            String dish_name = mMeal.getName();
            Toast.makeText(getActivity(),
                    dish_name + " clicked!", Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
            bundle.putString("meal_ID", String.valueOf(mMeal.getId())); // Put anything what you want

            Fragment showDetails = new ShowFoodDetailsFragment();
            showDetails.setArguments(bundle);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment_content_nav, showDetails)
                    .addToBackStack(null)
                    .commit();
        }
    }// MealsHolder


    //--------------------------------------------------------------------------------------------\\
    //--------------------------------------------------------------------------------------------\\
    private class DishAdapter extends RecyclerView.Adapter<MealsHolder> {
        private List<Meal> mMenu;

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
            holder.bind(meals);
        }

        @Override
        public int getItemCount() {
            return mMenu.size();
        }

    }// MealAdapter

}
