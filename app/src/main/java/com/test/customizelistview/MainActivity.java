package com.test.customizelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // list of items to store
    ArrayList<Product> productList = new ArrayList<Product>();
    private ArrayList<Cart> cart = new ArrayList<Cart>();
    Product selectedProduct;
    ListView lv;
    TextView qty, total;
    SeekBar sb;
    Button add;
    double totalAmount, currentPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        lv = (ListView) findViewById(R.id.lvProduct);
        qty = (TextView) findViewById(R.id.txvQuantityShow);
        total = (TextView) findViewById(R.id.txvTotal);
        sb = (SeekBar) findViewById(R.id.seekBar);
        add = (Button) findViewById(R.id.btnAdd);

        lv.setAdapter(new ProdAdapter(this, productList));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentPrice = productList.get(i).getPrice();
                selectedProduct = productList.get(i);
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                qty.setText(String.valueOf(sb.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQty = Integer.parseInt(qty.getText().toString());
                if (selectedProduct != null) {
                    addProductToCart(selectedProduct, currentQty);
                    updateTotal();
                }
            }
        });
    }

    public void fillData () {
        productList.add(new Product("Sweat shirt", 35, R.drawable.sweatshirt));
        productList.add(new Product("Long sleeve shirt", 52, R.drawable.longsleeve));
        productList.add(new Product("Polo shirt", 42, R.drawable.polo));
        productList.add(new Product("Women shirt", 53.5, R.drawable.womenshirt));
        productList.add(new Product("Women pant", 67, R.drawable.womenpant));
        productList.add(new Product("Men pant", 55, R.drawable.pantmen));
    }

    protected void addProductToCart (Product selectedProduct, int quantity) {
        Cart newItem = new Cart(selectedProduct, quantity);

        for (Cart item : cart) {
            if (item.getProduct().getName().equals(selectedProduct.getName())) {
                quantity = item.getQuantity() + quantity;
                newItem.setQuantity(quantity);
                break;
            }
        }

        cart.add(newItem);
    }

    protected void updateTotal () {
        double totalAmount = 0;
        for (Cart item : cart) {
            totalAmount += item.getProductTotal();
        }
        total.setText(String.format("$ %.2f", totalAmount));
    }
}