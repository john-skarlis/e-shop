package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class products_fragment extends Fragment implements View.OnClickListener {

    Button insert_btn, show_btn;
    public products_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.products_fragment_layout, container, false);

        //αντιστοιχιζω τα στοιχεια και οριζω ακροατες για τα κουμπια μου
        insert_btn = view.findViewById(R.id.insert_product);
        show_btn = view.findViewById(R.id.show_products);
        insert_btn.setOnClickListener(this);
        show_btn.setOnClickListener(this);

        return view;
    }

    //εδω αναλογα με το κουμπι που εκανε κλικ ο χρηστης εμφανιζω το αναλογο fragment
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert_product:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new insert_product_fragment()).addToBackStack(null).commit();
                break;
            case R.id.show_products:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new show_products_fragment()).addToBackStack(null).commit();
                break;
        }
    }
}
