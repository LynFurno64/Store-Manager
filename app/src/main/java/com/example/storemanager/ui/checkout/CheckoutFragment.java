package com.example.storemanager.ui.checkout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storemanager.NavActivity;
import com.example.storemanager.R;
import com.example.storemanager.database.Meal;
import com.example.storemanager.database.MenuLab;
import com.example.storemanager.databinding.FragmentCheckoutBinding;
import com.example.storemanager.ui.order.OrdersFragment;
import com.example.storemanager.ui.order.ShowFoodDetailsFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentCheckoutBinding binding;
    private RecyclerView mCartRecyclerView;
    private CartAdapter mAdapter;

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
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCheckoutBinding.inflate(getLayoutInflater());

        mCartRecyclerView = binding.rvCartList.findViewById(R.id.rv_cart_list);
        mCartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void updateUI() {
        MenuLab menuLab = MenuLab.get(getActivity());
        List<Meal> dishes = menuLab.getMenu();

        if (mAdapter == null) {
            mAdapter = new CartAdapter(dishes);
            mCartRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setMenu(dishes);
            mAdapter.notifyDataSetChanged();
        }
    }// updateUI

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.nav_show_delete, menu);
        MenuItem subtitleItem = menu.findItem(R.id.delete_meal);
        subtitleItem.setTitle(R.string.delete_meal);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // To prevent memory leak
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        updateUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    //---------------stores and recycles views as they are scrolled off screen--------------------\\
    //--------------------------------------------------------------------------------------------\\
    private class CartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Meal mCart;
        private TextView mTextViewTitle;
        private TextView mTextViewPrice;
        private TextView mTextViewAmount;

        public CartHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cardview_checkout, parent, false));
            itemView.setOnClickListener(this);
            mTextViewTitle = itemView.findViewById(R.id.cart_meal_title);
            mTextViewAmount = itemView.findViewById(R.id.cart_amount);
            mTextViewPrice = itemView.findViewById(R.id.cart_meal_price);
        }

        public void bind(Meal meal){
            mCart = meal;
            mTextViewTitle.setText(mCart.getName());
            mTextViewAmount.setText(String.valueOf(mCart.getTimesOrder()));
            mTextViewPrice.setText(String.valueOf(mCart.getPrice()));
        }// bind

        @Override
        public void onClick(View view) {

        }
    }// MealsHolder

    //--------------------------------------------------------------------------------------------\\
    //--------------------------------------------------------------------------------------------\\
    private class CartAdapter extends RecyclerView.Adapter<CartHolder> {
        private List<Meal> mMenu;

        public CartAdapter(List<Meal> meals) {
            mMenu = meals;
        }

        // inflates the row layout from xml when needed
        @Override
        public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CartHolder(layoutInflater, parent);
        }

        // binds the data into TexView for each row
        @Override
        public void onBindViewHolder(CartHolder holder, int position) {
            Meal meals = mMenu.get(position);
            holder.bind(meals);
        }

        @Override
        public int getItemCount() {
            return mMenu.size();
        }

        public void setMenu(List<Meal> meals){
            mMenu = meals;
        }

    }// MealAdapter
}