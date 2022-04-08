package com.test.customizelistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    ArrayList<Cart> cart;
    LayoutInflater inflater;
    Context context;

    public CartAdapter(Context context, ArrayList<Cart> cart) {
        this.cart = cart;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cart.size();
    }

    @Override
    public Object getItem(int i) {
        return cart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CartViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(R.layout.cart_row, null);
            viewHolder = new CartViewHolder();
            viewHolder.productName = view.findViewById(R.id.tvProductName);
            viewHolder.quantity = view.findViewById(R.id.tvQuantity);
            viewHolder.deleteCartItem = view.findViewById(R.id.ivDeleteCartItem);
        } else {
            viewHolder = (CartViewHolder) view.getTag();
        }

        viewHolder.productName.setText(cart.get(i).getProduct().getName());
        viewHolder.quantity.setText("" + cart.get(i).getQuantity());

        viewHolder.deleteCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.remove(cart.get(i));

                MainActivity.cart = cart;
            }
        });

        return view;
    }

    static class CartViewHolder {
        TextView productName, quantity;
        ImageView deleteCartItem;
    }

    public void refresh (ArrayList<Cart> cart) {

    }
}
