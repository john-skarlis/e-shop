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
public class customers_fragment extends Fragment implements View.OnClickListener {

    Button insert_btn, show_btn;

    public customers_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.customers_fragment_layout, container, false);
        //αρχικοποιω δυο κουμπια για να πατησει ο χρηστης αναλογα με αυτο που θελει να κανει και τα βαζω ακροατες
        insert_btn = view.findViewById(R.id.insert_customer);
        show_btn = view.findViewById(R.id.show_cusomers);
        insert_btn.setOnClickListener(this);
        show_btn.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        //φερνω το αναλογω fragment αναλογα με το κλικ του χρηστη
        switch (view.getId()) {
            case R.id.insert_customer:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new insert_customer_fragment()).addToBackStack(null).commit();
                break;
            case R.id.show_cusomers:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new show_customer_fragment()).addToBackStack(null).commit();
                break;
        }
    }
}
