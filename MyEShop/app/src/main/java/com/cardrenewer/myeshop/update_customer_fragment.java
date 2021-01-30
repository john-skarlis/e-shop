package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class update_customer_fragment extends Fragment implements View.OnClickListener {
    TextInputEditText name_edit, surname_edit, city_edit, phone_edit;
    TextInputLayout name_txt, surname_txt, city_txt, phone_txt;
    Button update_btn;
    String name;
    String surname;
    String city;
    String phone = "";
    int userid;

    public update_customer_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.update_customer_fragment_layout, container, false);

        //αντιστοιχιζω τα στοιχεια
        name_edit = view.findViewById(R.id.customer_name_txt_edit);
        name_txt = view.findViewById(R.id.customer_name_txt);
        surname_edit = view.findViewById(R.id.customer_surname_txt_edit);
        surname_txt = view.findViewById(R.id.customer_surname_txt);
        city_edit = view.findViewById(R.id.customer_city_txt_edit);
        city_txt = view.findViewById(R.id.customer_city_txt);
        phone_edit = view.findViewById(R.id.customer_phone_txt_edit);
        phone_txt = view.findViewById(R.id.customer_phone_txt);
        update_btn = view.findViewById(R.id.update_customer_form_btn);
        //ακροατης για το κουμπι
        update_btn.setOnClickListener(this);

        //παιρνω το id του χρηστη που θελουμε να γινει update με το bundle
        Bundle bundle = getArguments();
        userid = bundle.getInt("userid");
        //παιρνω τον πελατη απτην βαση με το συγκεκριμενο id (θα μπορουσα και χωρις List με να αντικειμενο customers)
        //και τα βαζω στα TextInputEditText ετσι ωστε να σβηνει και αλλαζεται
        List<customers> customer = MainActivity.myAppDatabase.myDao().get_update_customer(userid);

        for (customers c : customer) {
            name = c.getName();
            surname = c.getSurname();
            city = c.getCity();
            phone = c.getPhone_number();
        }

        name_edit.setText(name);
        surname_edit.setText(surname);
        city_edit.setText(city);
        phone_edit.setText(phone);


        return view;
    }

    //στο κλικ κανω ενα αντικειμενο customer και το κανω set τα πεδια του και το περναω στο ερωτημα για να γινει το update
    //καλω και τους ελεγχους
    public void onClick(View v) {
        if (!validate_names()) {
            return;
        } else if (!validate_surname()) {
            return;
        } else if (!validate_city()) {
            return;
        } else if (!validate_phone()) {
            return;
        } else {
            customers customer = new customers();
            customer.setId(userid);
            customer.setName(name_edit.getText().toString());
            customer.setSurname(surname_edit.getText().toString());
            customer.setCity(city_edit.getText().toString());
            customer.setPhone_number(phone_edit.getText().toString());
            MainActivity.myAppDatabase.myDao().update_customer(customer);
            Toast.makeText(getActivity(), "Επιτυχής ενημέρωση στοιχείων", Toast.LENGTH_LONG).show();
        }

    }

    //κανω validate ολα τα παιδια ενεργοποιοντας το seterror ενω χρησιμοποιω την request focus για το πληκτρολογιο
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validate_names() {
        if (name_edit.getText().toString().trim().isEmpty()) {
            name_txt.setError("Συμπλήρωσε όνομα");
            requestFocus(name_edit);
            return false;
        } else {
            name_txt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_surname() {
        if (surname_edit.getText().toString().trim().isEmpty()) {
            surname_txt.setError("Συμπλήρωσε επίθετο");
            requestFocus(surname_edit);
            return false;
        } else {
            surname_txt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_city() {
        if (city_edit.getText().toString().trim().isEmpty()) {
            city_txt.setError("Συμπλήρωσε πόλη");
            requestFocus(city_edit);
            return false;
        } else {
            city_txt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_phone() {
        if (phone_edit.getText().toString().trim().isEmpty()) {
            phone_txt.setError("Συμπλήρωσε τηλέφωνο");
            requestFocus(phone_edit);
            return false;
        } else if (phone_edit.getText().toString().length() != 10) {
            phone_txt.setError("Πρέπει να είναι 10 χαρακτήρες");
            requestFocus(phone_edit);
            return false;
        } else {
            phone_txt.setErrorEnabled(false);
        }
        return true;
    }


}
