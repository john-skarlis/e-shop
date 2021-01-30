package com.cardrenewer.myeshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<String> mData;

    //παιρνω την λιστα των κατηγοριων
    public RecyclerViewAdapter(List<String> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //βαζω τι διαπαφη θα κανει inflate για καθε στοιχειο του πινακα ο recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout_list_item, parent, false);
        //φτιαχνω ενα αντικειμενο και στελνω την διεπαφη
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //οριζω το κειμενο αλλα και μια φωτογραφία για καθε κατηγορια
        holder.tv_category_title.setText(mData.get(position));
        if (mData.get(position).equals("Υπολογιστές")) {
            holder.iv_category_icon.setImageResource(R.mipmap.baseline_computer_black_48);
        } else if (mData.get(position).equals("Κινητά")) {
            holder.iv_category_icon.setImageResource(R.mipmap.baseline_phone_android_black_48);
        }
        if (mData.get(position).equals("Κάμερες")) {
            holder.iv_category_icon.setImageResource(R.mipmap.baseline_photo_camera_black_48);
        } else if (mData.get(position).equals("Τηλεοράσεις")) {
            holder.iv_category_icon.setImageResource(R.mipmap.baseline_tv_black_48);
        } else if (mData.get(position).equals("Ταμπλέτες")) {
            holder.iv_category_icon.setImageResource(R.mipmap.baseline_tablet_black_48);
        } else if (mData.get(position).equals("Ακουστικά")) {
            holder.iv_category_icon.setImageResource(R.mipmap.baseline_headset_mic_black_48);
        } else if (mData.get(position).equals("Παιχνιδομηχανές")) {
            holder.iv_category_icon.setImageResource(R.mipmap.baseline_videogame_asset_black_48);
        } else if (mData.get(position).equals("Smartwatch")) {
            holder.iv_category_icon.setImageResource(R.mipmap.baseline_watch_black_48);
        }
        //και εναν ακροατη για καθες στοιχειο οποτε στο κλικ καλειται ενα fragment με την κατηγορια που εχει επιλεξει ο χρηστης και στελνεται με το bundle
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_products_fragment search_products_fragment = new search_products_fragment();
                Bundle bundle = new Bundle();
                bundle.putString("category",mData.get(position));
                search_products_fragment.setArguments(bundle);
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,search_products_fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_category_title;
        ImageView iv_category_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            //φτιαχνω ενα αντικειμενο και στελνω την διεπαφη
            iv_category_icon = itemView.findViewById(R.id.card_icon);
            tv_category_title = itemView.findViewById(R.id.card_title);
        }
    }


}
