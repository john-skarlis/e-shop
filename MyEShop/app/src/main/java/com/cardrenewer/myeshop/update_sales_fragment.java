package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class update_sales_fragment extends Fragment implements View.OnClickListener {
    TextInputEditText proid_edit, custid_edit, quantity_edit, date_edit;
    TextInputLayout proid_txt, custid_txt, quantity_txt, date_txt;
    Button update_btn;
    int sid = 0;

    public update_sales_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.update_sales_fragment_layout, container, false);

        //αντιστοιχιζω τα στοιχεια
        proid_edit = view.findViewById(R.id.product_id_txt_edit);
        proid_txt = view.findViewById(R.id.product_id_txt);
        custid_edit = view.findViewById(R.id.customer_id_txt_edit);
        custid_txt = view.findViewById(R.id.customer_id_txt);
        quantity_edit = view.findViewById(R.id.sale_quantity_txt_edit);
        quantity_txt = view.findViewById(R.id.sale_quantity_txt);
        date_edit = view.findViewById(R.id.sale_date_txt_edit);
        date_txt = view.findViewById(R.id.sale_date_txt);
        update_btn = view.findViewById(R.id.update_sale_form_btn);
        //ακροατης για το κουμπι
        update_btn.setOnClickListener(this);
        //παιρνω το id της πωλησης που θελουμε να γινει update με το bundle
        Bundle bundle = getArguments();
        sid = bundle.getInt("sid");
        //παιρνω την πωληση απτην βαση με το συγκεκριμενο id
        //και τα βαζω στα TextInputEditText ετσι ωστε να σβηνει και αλλαζεται
        sales sale = MainActivity.myAppDatabase.myDao().get_update_sale(sid);
        proid_edit.setText(String.valueOf(sale.getPid()));
        custid_edit.setText(String.valueOf(sale.getCid()));
        quantity_edit.setText(String.valueOf(sale.getSale_quantity()));
        date_edit.setText(sale.getDate());


        return view;
    }

    //στο κλικ κανω ενα αντικειμενο sale και το κανω set τα πεδια του και το περναω στο ερωτημα για να γινει το update
    //καλω και τους ελεγχους
    @Override
    public void onClick(View v) {
        if (!validate(proid_txt, proid_edit)) {
            return;
        } else if (!validate(custid_txt, custid_edit)) {
            return;
        } else if (!validate(quantity_txt, quantity_edit)) {
            return;
        } else if (!validate(date_txt, date_edit)) {
            return;
        } else {
            sales sale = new sales();
            sale.setId(sid);
            sale.setPid(Integer.parseInt(proid_edit.getText().toString()));
            sale.setCid(Integer.parseInt(custid_edit.getText().toString()));
            sale.setSale_quantity(Integer.parseInt(quantity_edit.getText().toString()));
            sale.setDate(date_edit.getText().toString());
            MainActivity.myAppDatabase.myDao().update_sale(sale);
            Toast.makeText(getActivity(), "Επιτυχής ενημέρωση στοιχείων", Toast.LENGTH_LONG).show();

        }

    }

    //κανω validate ολα τα παιδια ενεργοποιοντας το seterror ενω χρησιμοποιω την request focus για το πληκτρολογιο
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validate(TextInputLayout t, TextInputEditText e) {
        if (e.getText().toString().trim().isEmpty()) {
            t.setError("Το πεδίο είναι υποχρεωτικό");
            requestFocus(e);
            return false;
        } else {
            t.setErrorEnabled(false);
        }
        return true;
    }
}
