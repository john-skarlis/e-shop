package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class home_fragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    private List<String> cat_list;


    public home_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);

        //παιρνω τις κατηγοριες οι οποιες ειναι array string
        cat_list = Arrays.asList(getResources().getStringArray(R.array.categories));

        //αντιστοιχιζω τον recycler view και οριζω ενα gridlayout και βαζω στον ανταπτορα τις κατηγοριες
        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new RecyclerViewAdapter(cat_list);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
