package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class show_products_fragment extends Fragment implements View.OnClickListener {
    TextView products_view;
    Button update_btn, delete_btn;
    EditText id_txt;
    String result = "";

    public show_products_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.show_products_fragment_layout, container, false);
        //αντιστοιχιζω τα στοιχεια
        products_view = view.findViewById(R.id.show_products_view);
        id_txt = view.findViewById(R.id.id_product_holder);
        update_btn = view.findViewById(R.id.update_p_btn);
        delete_btn = view.findViewById(R.id.delete_p_btn);

        //καλω την μεθοδο show_products οπου τραβαει ολες τις εγγραφες του πινακα products και τις εμφανιζει
        result = show_products();
        products_view.setText(result);


        update_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);

        return view;
    }

    //αν πατηθει το update καλω την μεθοδο sendid αλλιως αν πατηθει το delete εκτελω ενα delete ερωτημα οπου εχω εχω το αντικειμο με το id που εβαλε ο χρηστης
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_p_btn:
                if (!validate_id()) {
                    break;
                }
                sendid();
                break;
            case R.id.delete_p_btn:
                int prodid = 0;
                if (!validate_id()) {
                    break;
                }
                try {
                    prodid = Integer.parseInt(id_txt.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Δώσε αριθμό: " + ex);
                }
                products product = new products();
                product.setId(prodid);
                MainActivity.myAppDatabase.myDao().delete_product(product);
                products_view.setText("");
                result = "";
                result = show_products();
                products_view.setText(result);
                Toast.makeText(getActivity(), "Επιτυχής διαγραφή", Toast.LENGTH_LONG).show();
                id_txt.setText("");

        }
    }

    //εμφανιζει ολα τα προιοντα με μια for που κανει στην λιστα products που γεμζει απτο ερωτημα get_products
    private String show_products() {
        List<products> products = MainActivity.myAppDatabase.myDao().get_products();
        String result = "";
        for (products p : products) {
            int id = p.getId();
            String name = p.getName();
            String description = p.getDescription();
            int quantity = p.getQuantity();
            String category_name = p.getCategory_name();
            double price = p.getPrice();
            result = result + "\nID προϊόντος: " + id + "\nΌνομα προϊόντος: " + name + "\nΠεριγραφή: " + description + "\nΠοσότητα: " + quantity + "\nΤιμή: " + price + "\nΚατηγορία: " + category_name + "\n";
        }
        return result;
    }

    private boolean validate_id() {
        if (id_txt.getText().toString().trim().isEmpty()) {
            Toast.makeText(getActivity(), "Συμπλήρωσε αριθμό ID", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    //βαζω στο bundle το id που έδωσε ο χρηστης και καλω με τον fragment manager το update_product_fragment στελνοντας και το bundle μαζι
    public void sendid() {
        int prodid = 0;
        try {
            prodid = Integer.parseInt(id_txt.getText().toString());
        } catch (NumberFormatException ex) {
            System.out.println("Δεν έγινε η μετατροπή σωστα " + ex);
        }
        update_product_fragment update_product_fragment = new update_product_fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("prodid", prodid);
        update_product_fragment.setArguments(bundle);
        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, update_product_fragment).addToBackStack(null).commit();
    }

}
