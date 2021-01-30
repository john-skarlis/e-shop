package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class insert_customer_fragment extends Fragment {

    TextInputLayout insert_name, insert_surname, insert_city, insert_phone;
    TextInputEditText insert_name_edit, insert_surname_edit, insert_city_edit, insert_phone_edit;
    Button insert_btn;

    public insert_customer_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.insert_customer_fragment_layout, container, false);

        //antistoixizw ta stoixeia thw formas
        insert_name = view.findViewById(R.id.customer_name_txt);
        insert_name_edit = view.findViewById(R.id.customer_name_txt_edit);
        insert_surname = view.findViewById(R.id.customer_surname_txt);
        insert_surname_edit = view.findViewById(R.id.customer_surname_txt_edit);
        insert_city = view.findViewById(R.id.customer_city_txt);
        insert_city_edit = view.findViewById(R.id.customer_city_txt_edit);
        insert_phone = view.findViewById(R.id.customer_phone_txt);
        insert_phone_edit = view.findViewById(R.id.customer_phone_txt_edit);
        insert_btn = view.findViewById(R.id.insert_customer_form_btn);
        //orizw listener kai ti tha kanei
        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate_names()) {
                    return;
                }
                if (!validate_surname()) {
                    return;
                }
                if (!validate_city()) {
                    return;
                }
                if (!validate_phone()) {
                    return;
                } else {
                    //pairnw ta stoixeia tou xristh kai setarw ena antikeimeno typou customers kai ektelw thn add_customer opou mou vgazei ena toast oti ola kala
                    String name = insert_name_edit.getText().toString();
                    String surname = insert_surname_edit.getText().toString();
                    String city = insert_city_edit.getText().toString();
                    String phone = insert_phone_edit.getText().toString();
                    customers customer = new customers();
                    customer.setName(name);
                    customer.setSurname(surname);
                    customer.setCity(city);
                    customer.setPhone_number(phone);
                    MainActivity.myAppDatabase.myDao().add_customer(customer);
                    Toast.makeText(getActivity(), "Επιτυχης εισαγωγή", Toast.LENGTH_LONG).show();
                    insert_name_edit.setText("");
                    insert_surname_edit.setText("");
                    insert_city_edit.setText("");
                    insert_phone_edit.setText("");
                }
            }
        });


        return view;
    }

    //κανω validate ολα τα παιδια ενεργοποιοντας το seterror ενω χρησιμοποιω την request focus για το πληκτρολογιο
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validate_names() {
        if (insert_name_edit.getText().toString().trim().isEmpty()) {
            insert_name.setError("Συμπλήρωσε όνομα");
            requestFocus(insert_name_edit);
            return false;
        } else if (insert_name_edit.getText().toString().length() < 3) {
            insert_name.setError("Περισσοτερους από 3 χαρακτήρες");
            requestFocus(insert_name_edit);
            return false;

        } else {
            insert_name.setErrorEnabled(false);
        }
        return true;

    }

    private boolean validate_surname() {
        if (insert_surname_edit.getText().toString().trim().isEmpty()) {
            insert_surname.setError("Συμπλήρωσε επίθετο");
            requestFocus(insert_surname_edit);
            return false;
        } else if (insert_surname_edit.getText().toString().length() < 3) {
            insert_surname.setError("Περισσοτερους από 3 χαρακτήρες");
            requestFocus(insert_surname_edit);
            return false;

        } else {
            insert_surname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_city() {
        if (insert_city_edit.getText().toString().trim().isEmpty()) {
            insert_city.setError("Συμπλήρωσε πόλη");
            requestFocus(insert_city_edit);
            return false;
        } else if (insert_city_edit.getText().toString().length() < 3) {
            insert_city.setError("Περισσοτερους από 3 χαρακτήρες");
            requestFocus(insert_city_edit);
            return false;

        } else {
            insert_city.setErrorEnabled(false);
        }
        return true;

    }

    private boolean validate_phone() {
        if (insert_phone_edit.getText().toString().trim().isEmpty()) {
            insert_phone.setError("Συμπλήρωσε τηλέφωνο");
            requestFocus(insert_phone_edit);
            return false;
        } else if (insert_phone_edit.getText().toString().length() != 10) {
            insert_phone.setError("Πρέπει να είναι 10 χαρακτήρες");
            requestFocus(insert_phone_edit);
            return false;

        } else {
            insert_phone.setErrorEnabled(false);
        }
        return true;

    }
}
