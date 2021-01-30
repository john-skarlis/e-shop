package com.cardrenewer.myeshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recycle_cart_adapter extends RecyclerView.Adapter<Recycle_cart_adapter.mHolder> {

    private List<cart_item_result> cart_list;

    //παιρνω την λιστα τ αντικειμενα που θελω να εμφανιζω
    public Recycle_cart_adapter(List<cart_item_result> cart_list) {
        this.cart_list = cart_list;
    }

    @NonNull
    @Override
    public mHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //βαζω τι διαπαφη θα κανει inflate για καθε στοιχειο του πινακα ο recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
        //φτιαχνω ενα αντικειμενο και στελνω την διεπαφη
        mHolder myHolder = new mHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull mHolder holder, int position) {

        //εμφανιζω ονομα και επιθετο πελατη, ονομα προιοντος ,τιμη, και ποσοτητα παραγγελιας
        String result = cart_list.get(position).getCustomer_name() + " " +  cart_list.get(position).getCustomer_surname();
        holder.tv_cname.setText(result);
        holder.tv_pname.setText(cart_list.get(position).getProduct_name());
        holder.tv_price.setText(Double.toString(cart_list.get(position).getProduct_price()));
        holder.tv_quantity.setText(Integer.toString(cart_list.get(position).getCart_quantity()));

    }

    @Override
    public int getItemCount() {
        return cart_list.size();
    }

    public static class mHolder extends RecyclerView.ViewHolder{

        TextView tv_pname,tv_cname,tv_price,tv_quantity;

        public mHolder(View itemView){
            super(itemView);
            //αντιστοιχιζω τα στοιχεια της διεπαφης
            tv_pname = itemView.findViewById(R.id.productname);
            tv_cname = itemView.findViewById(R.id.customername);
            tv_price= itemView.findViewById(R.id.price);
            tv_quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
