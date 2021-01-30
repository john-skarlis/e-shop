package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class search_products_fragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Recylcer_List_Adapter adapter;
    private List<products> prod_list;

    public search_products_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.search_products_fragment_layout, container, false);

        //παιρνω τηνκατηγορια που επελεξε ο χρηστης με το bundle
        Bundle bundle = getArguments();
        String category = bundle.getString("category");

        //αντιστοιχιζω τον recycler
        recyclerView = view.findViewById(R.id.recyclerview_list);
        //παιρνω ολα τα προιοντα που ανηκουν σε αυτη την λιστα
        prod_list = MainActivity.myAppDatabase.myDao().get_category_products(category);
        //αρχικοποιω και οριζω τον ανταπτορα και το layout
        adapter = new Recylcer_List_Adapter(prod_list);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
