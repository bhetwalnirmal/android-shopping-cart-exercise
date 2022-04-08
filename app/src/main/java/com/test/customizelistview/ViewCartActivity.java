package com.test.customizelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewCartActivity extends AppCompatActivity {
    ListView cart;
    ArrayList<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        cart = (ListView) findViewById(R.id.lvCart);
        Intent intent = getIntent();

        cartList = (ArrayList<Cart>) intent.getSerializableExtra("cart");


        cart.setAdapter(new CartAdapter(this, cartList));
    }

    public void refreshCartAdapter () {
        cart.getAdapter().notifyAll();
    }
}