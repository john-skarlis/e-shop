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
public class manage_fragment extends Fragment implements View.OnClickListener {
    Button customers_btn, sales_btn, products_btn,query_btn;

    public manage_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.manage_fragment_layout, container, false);

        //αντιστοιχιζω τα στοιχεια και οριζω ακροατες για τα κουμπια μου
        customers_btn = view.findViewById(R.id.customers_button);
        products_btn = view.findViewById(R.id.products_button);
        sales_btn = view.findViewById(R.id.sales_button);
        query_btn = view.findViewById(R.id.queries_button);
        customers_btn.setOnClickListener(this);
        products_btn.setOnClickListener(this);
        sales_btn.setOnClickListener(this);
        query_btn.setOnClickListener(this);

        return view;
    }
    //εδω αναλογα με το κουμπι που εκανε κλικ ο χρηστης εμφανιζω το αναλογο fragment
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.customers_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new customers_fragment()).addToBackStack(null).commit();
                break;
            case R.id.products_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new products_fragment()).addToBackStack(null).commit();
                break;
            case R.id.sales_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new sales_fragment()).addToBackStack(null).commit();
                break;
            case R.id.queries_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new queries_fragment()).addToBackStack(null).commit();
        }
    }
}
