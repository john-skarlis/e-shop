package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class insert_product_fragment extends Fragment implements View.OnClickListener {

    TextInputLayout name_txt, quantity_txt, description_txt, price_txt;
    TextInputEditText name_edit, quantity_edit, description_edit, price_edit;
    Button insert_btn;
    Spinner categories_spinner;
    ArrayAdapter<CharSequence> adapter;
    String catname = "";

    public insert_product_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.insert_product_fragmanet_layout, container, false);


        //αντιστοιχιζω τα στοιχεια μου και θετω ακροατη
        name_txt = view.findViewById(R.id.product_name_txt);
        name_edit = view.findViewById(R.id.product_name_txt_edit);
        description_txt = view.findViewById(R.id.product_description_txt);
        description_edit = view.findViewById(R.id.product_description_txt_edit);
        quantity_txt = view.findViewById(R.id.product_quantity_txt);
        quantity_edit = view.findViewById(R.id.product_quantity_txt_edit);
        price_txt = view.findViewById(R.id.product_price_txt);
        price_edit = view.findViewById(R.id.product_price_txt_edit);
        insert_btn = view.findViewById(R.id.insert_product_form_btn);
        categories_spinner = view.findViewById(R.id.categories_spinner);
        insert_btn.setOnClickListener(this);


        //εχω ενα σπινερ για τις κατηγοριες που μπορει να προστεθει το προιον
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.categories, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        categories_spinner.setAdapter(adapter);
        categories_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    //καλω τους ελεγχους και φτιαχνω ενα αντικειμενο τυπου products και το περναω στην βαση
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
                quantity = Integer.parseInt(quantity_edit.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Δεν έγινε η μετατροπή σωστα " + ex);
            }
            try {
                price = Double.parseDouble(price_edit.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Βάλε τον αριθμό μετά κώμα και τα δεκαδικά ψηφία π.χ. 107.00\n" + ex);
            }
            products product = new products();
            product.setName(name_edit.getText().toString());
            product.setDescription(description_edit.getText().toString());
            product.setQuantity(quantity);
            product.setCategory_name(catname);
            product.setPrice(price);
            MainActivity.myAppDatabase.myDao().add_product(product);
            Toast.makeText(getActivity(), "Επιτυχής εισαγώγη", Toast.LENGTH_LONG).show();
            name_edit.setText("");
            description_edit.setText("");
            quantity_edit.setText("");
            price_edit.setText("");
        }
    }

    //κανω validate ολα τα παιδια ενεργοποιοντας το seterror ενω χρησιμοποιω την request focus για το πληκτρολογιο
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validate_name() {
        if (name_edit.getText().toString().trim().isEmpty()) {
            name_txt.setError("Συμπλήρωσε όνομα");
            requestFocus(name_edit);
            return false;
        } else {
            name_txt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_description() {
        if (description_edit.getText().toString().trim().isEmpty()) {
            description_txt.setError("Συμπλήρωσε περιγραφή");
            requestFocus(description_edit);
            return false;
        } else {
            description_txt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_quantity() {
        if (quantity_edit.getText().toString().trim().isEmpty()) {
            quantity_txt.setError("Συμπλήρωσε ποσότητα");
            requestFocus(quantity_edit);
            return false;
        } else {
            quantity_txt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_price() {
        if (price_edit.getText().toString().trim().isEmpty()) {
            price_txt.setError("Συμπλήρωσε τιμή");
            requestFocus(price_edit);
            return false;
        } else {
            price_txt.setErrorEnabled(false);
        }
        return true;
    }


}
