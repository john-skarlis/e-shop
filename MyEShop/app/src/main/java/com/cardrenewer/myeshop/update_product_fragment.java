package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class update_product_fragment extends Fragment implements View.OnClickListener {

    TextInputLayout p_name_txt, p_description_txt, p_quantity_txt, p_price_txt;
    TextInputEditText p_name_txt_edit, p_description_txt_edit, p_quantity_txt_edit, p_price_txt_edit;
    Spinner categories_spinner;
    Button update_btn;
    int prodid;
    String name;
    String description;
    String s_quantity;
    String catname = "";
    String s_price = "";
    ArrayAdapter<CharSequence> adapter;

    public update_product_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.update_product_fragment_layout, container, false);

        //αντιστοιχιζω τα στοιχεια
        p_name_txt = view.findViewById(R.id.p_name_txt);
        p_name_txt_edit = view.findViewById(R.id.p_name_txt_edit);
        p_description_txt = view.findViewById(R.id.p_description_txt);
        p_description_txt_edit = view.findViewById(R.id.p_description_txt_edit);
        p_quantity_txt = view.findViewById(R.id.p_quantity_txt);
        p_quantity_txt_edit = view.findViewById(R.id.p_quantity_txt_edit);
        p_price_txt = view.findViewById(R.id.p_price_txt);
        p_price_txt_edit = view.findViewById(R.id.p_price_txt_edit);
        categories_spinner = view.findViewById(R.id.p_categories_spinner);
        update_btn = view.findViewById(R.id.p_update_form_btn);
        //ακροατης για το κουμπι
        update_btn.setOnClickListener(this);
        //παιρνω το id του χρηστη που θελουμε να γινει update με το bundle
        Bundle bundle = getArguments();
        prodid = bundle.getInt("prodid");
        //παιρνω το προιον απτην βαση με το συγκεκριμενο id (θα μπορουσα και χωρις List με να αντικειμενο products)
        //και τα βαζω στα TextInputEditText ετσι ωστε να σβηνει και αλλαζεται
        List<products> product = MainActivity.myAppDatabase.myDao().get_update_product(prodid);
        for (products p : product) {
            name = p.getName();
            description = p.getDescription();
            s_quantity = String.valueOf(p.getQuantity());
            catname = p.getCategory_name();
            s_price = String.valueOf(p.getPrice());
        }

        p_name_txt_edit.setText(name);
        p_description_txt_edit.setText(description);
        p_quantity_txt_edit.setText(s_quantity);
        p_price_txt_edit.setText(s_price);

        //χρησιμοποιω και ενα σπινερ οπου εχει τις κατηγοριες για να αλλαξουμε που ανηκει το προιον
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        categories_spinner.setAdapter(adapter);
        //στο ανοιγμα της διεπαφη οριζω τον σπινερ να ανοιξει στην θεση οπου ειναι το ονομα που ειχε στην αρχη το προιον
        categories_spinner.setSelection(adapter.getPosition(catname));
        categories_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //στο κλικ παιρνω την κατηγορια που διαλεξε
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catname = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    //στο κλικ κανω ενα αντικειμενο product και το κανω set τα πεδια του και το περναω στο ερωτημα για να γινει το update
    //καλω και τους ελεγχους
    @Override
    public void onClick(View v) {
        if (!validate_name()) {
            return;
        }
        if (!validate_description()) {
            return;
        }
        if (!validate_quantity()) {
            return;
        }
        if (!validate_price()) {
            return;
        } else {
            int quantity = 0;
            double price = 0;
            try {
                quantity = Integer.parseInt(p_quantity_txt_edit.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Δεν έγινε η μετατροπή σωστα " + ex);
            }
            try {
                price = Double.parseDouble(p_price_txt_edit.getText().toString());
            }catch (NumberFormatException ex){
                System.out.println("Βάλε τον αριθμό μετά κώμα και τα δεκαδικά ψηφία π.χ. 107.00\n" + ex);
            }
            products product = new products();
            product.setId(prodid);
            product.setName(p_name_txt_edit.getText().toString());
            product.setDescription(p_description_txt_edit.getText().toString());
            product.setQuantity(quantity);
            product.setCategory_name(catname);
            product.setPrice(price);
            MainActivity.myAppDatabase.myDao().update_product(product);
            Toast.makeText(getActivity(), "Επιτυχής ενημέρωση στοιχείων", Toast.LENGTH_LONG).show();
        }

    }

    //κανω validate ολα τα παιδια ενεργοποιοντας το seterror ενω χρησιμοποιω την request focus για το πληκτρολογιο
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validate_name() {
        if (p_name_txt_edit.getText().toString().trim().isEmpty()) {
            p_name_txt.setError("Συμπλήρωσε όνομα");
            requestFocus(p_name_txt_edit);
            return false;
        } else {
            p_name_txt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_description() {
        if (p_description_txt_edit.getText().toString().trim().isEmpty()) {
            p_description_txt.setError("Συμπλήρωσε περιγραφή");
            requestFocus(p_description_txt_edit);
            return false;
        } else {
            p_description_txt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_quantity() {
        if (p_quantity_txt_edit.getText().toString().trim().isEmpty()) {
            p_quantity_txt.setError("Συμπλήρωσε ποσότητα");
            requestFocus(p_quantity_txt_edit);
            return false;
        } else {
            p_quantity_txt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_price() {
        if (p_price_txt_edit.getText().toString().trim().isEmpty()) {
            p_price_txt.setError("Συμπλήρωσε τιμή");
            requestFocus(p_price_txt_edit);
            return false;
        } else {
            p_price_txt.setErrorEnabled(false);
        }
        return true;
    }

}
