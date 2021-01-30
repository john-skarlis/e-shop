package com.cardrenewer.myeshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recylcer_List_Adapter extends RecyclerView.Adapter<Recylcer_List_Adapter.MyHolder> {
    private List<products> products_list;

    //παιρνω την λιστα με τα προιοντα
    public Recylcer_List_Adapter(List<products> products_list) {
        this.products_list = products_list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //βαζω τι διαπαφη θα κανει inflate για καθε στοιχειο του πινακα ο recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linerar_list_item,parent,false);
        //φτιαχνω ενα αντικειμενο και στελνω την διεπαφη
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        //οριζω στα στοιχεια της διεπαφης κειμενο
        holder.tv_name.setText(products_list.get(position).getName());
        holder.tv_description.setText(products_list.get(position).getDescription());
        String price = Double.toString(products_list.get(position).getPrice());
        holder.tv_price.setText(price);

        //ενας ακροατης για καθε αντκειμενο ωστε να καλεσω το επομενο fragment στελνοντας το id απο το προιον που επελεξε ο χρηστης με το bundle
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                full_view_product full_view_product = new full_view_product();
                Bundle bundle = new Bundle();
                bundle.putInt("product",products_list.get(position).getId());
                full_view_product.setArguments(bundle);
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,full_view_product).addToBackStack(null).commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return products_list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_description;
        TextView tv_price;
        public MyHolder(View itemView){
            super(itemView);
            //φτιαχνω ενα αντικειμενο και στελνω την διεπαφη
            tv_name = itemView.findViewById(R.id.tv_p_name);
            tv_description = itemView.findViewById(R.id.tv_p_description);
            tv_price = itemView.findViewById(R.id.tv_p_price);
        }
    }
}
