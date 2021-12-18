package com.example.storemanager.ui.checkout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.storemanager.Helpers.OrderManager;
import com.example.storemanager.R;
import com.example.storemanager.database.CartFood;
import com.example.storemanager.databinding.FragmentCheckoutBinding;
import com.example.storemanager.ui.order.OrdersFragment;

import java.util.List;

public class CheckoutFragment extends Fragment {

    FragmentCheckoutBinding binding;
    private RecyclerView mCartRecyclerView;
    private CartAdapter mAdapter;
    private OrderManager orderManager = OrderManager.get(getActivity());
    private int grandTotal = (int) orderManager.getTotalCartPrice(orderManager.getCartItems());
    private String cartTotal = "$" + grandTotal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCheckoutBinding.inflate(getLayoutInflater());

        mCartRecyclerView = binding.rvCartList.findViewById(R.id.rv_cart_list);
        mCartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        binding.tvTotal.setText(cartTotal);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void updateUI() {
        OrderManager orderManager = OrderManager.get(getActivity());
        List<CartFood> items = orderManager.getCartItems();

        if (mAdapter == null) {
            mAdapter = new CartAdapter(items);
            mCartRecyclerView.setAdapter(mAdapter);
        } else {
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
        orderManager.setTotalCartPrice(0.00); // Set Cart total to 0.00
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
        private CartFood mCart;
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

        public void bind(CartFood items){
            mCart = items;
            mTextViewTitle.setText(mCart.getCart_name());
            mTextViewAmount.setText(String.valueOf(mCart.getCart_amount()));
            mTextViewPrice.setText(String.valueOf(mCart.getTotal()));

        }// bind


        @Override
        public void onClick(View view) {

        }
    }// MealsHolder

    //--------------------------------------------------------------------------------------------\\
    //--------------------------------------------------------------------------------------------\\
    private class CartAdapter extends RecyclerView.Adapter<CartHolder> {
        private List<CartFood> mCart;

        public CartAdapter(List<CartFood> items) {
            mCart = items;
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
            CartFood cart = mCart.get(position);
            holder.bind(cart);
        }

        @Override
        public int getItemCount() {
            return mCart.size();
        }

        public void setMenu(List<CartFood> items){
            mCart = items;
        }


    }// MealAdapter
}