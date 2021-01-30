package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class full_view_product extends Fragment implements View.OnClickListener {
    private products product;

    TextView title, description, quantity, price;
    Button add_btn;
    int product_id = 0;

    public full_view_product() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.full_view_product_layout, container, false);

        //αντιστοιχιζω τα στοιχεια μου και αρχικοποιω ακροατη για το κουμπι
        title = view.findViewById(R.id.product_title);
        description = view.findViewById(R.id.product_description);
        quantity = view.findViewById(R.id.tv_quantity);
        price = view.findViewById(R.id.tv_price);
        add_btn = view.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);

        //παιρνω τις παραμετρους που σταλθηκαν απτο προηγουμενο fragment οπου ηταν ο recycler με τα προιοντα
        //με την χρηση του bundle παιρνω την παραμετρο οπου ειναι το προιον που θελει να δει ο χρηστης
        Bundle bundle = getArguments();
        int p_id = bundle.getInt("product");
        //παιρνω το προιον
        product = MainActivity.myAppDatabase.myDao().get_product(p_id);

        //το εμφανιζω
        product_id = product.getId();
        title.setText(product.getName());
        description.setText(product.getDescription());
        quantity.setText(Integer.toString(product.getQuantity()));
        price.setText(Double.toString(product.getPrice()));


        return view;
    }

    //οταν γινει κλικ στο κουμπι προσθηκη αρχικοποιω και εμφανιζω το dialog
    @Override
    public void onClick(View v) {
        int quantity = product.getQuantity();
        List<customers> customers = MainActivity.myAppDatabase.myDao().get_customers();
        dialog_fragment dialog_fragment = new dialog_fragment(customers, quantity, product_id);
        dialog_fragment.show(getFragmentManager(), "Dialog");

    }

}
