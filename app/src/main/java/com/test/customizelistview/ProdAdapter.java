package com.test.customizelistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProdAdapter extends BaseAdapter {
    private ArrayList<Product> prodList;
    private LayoutInflater inflater; // we need it to connect the objects with the list_row.xml

    public ProdAdapter(Context context, ArrayList<Product> prodList) {
        this.prodList = prodList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return prodList.size();
    }

    @Override
    public Object getItem(int i) {
        return prodList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(R.layout.list_row, null);
            viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.txvName);
            viewHolder.price = view.findViewById(R.id.txvPrice);
            viewHolder.img = view.findViewById(R.id.imProd);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(prodList.get(i).getName());
        viewHolder.price.setText("$ " + prodList.get(i).getPrice());
        viewHolder.img.setImageResource(prodList.get(i).getImage());

        return view;
    }

    static class ViewHolder  {
        private TextView name;
        private TextView price;
        private ImageView img;

    }
}
