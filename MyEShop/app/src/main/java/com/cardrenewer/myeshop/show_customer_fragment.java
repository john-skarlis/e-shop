package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class show_customer_fragment extends Fragment implements View.OnClickListener {

    TextView customers_view;
    Button update_btn, delete_btn;
    EditText id_txt;
    String result = "";

    public show_customer_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.show_customer_fragment_layout, container, false);

        //αντιστοιχιζω τα στοιχεια
        update_btn = view.findViewById(R.id.update_btn);
        delete_btn = view.findViewById(R.id.delete_btn);
        id_txt = view.findViewById(R.id.id_holder);
        customers_view = view.findViewById(R.id.show_customers_view);

        //καλω την μεθοδο show_customers οπου τραβαει ολες τις εγγραφες του πινακα customers και τις εμφανιζει
        result = show_customers();
        customers_view.setText(result);


        update_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);

        return view;
    }

    //εμφανιζει ολες τους πελατες με μια for που κανει στην λιστα customers που γεμζει απτο ερωτημα get_customers
    public String show_customers() {
        List<customers> customers = MainActivity.myAppDatabase.myDao().get_customers();
        String result = "";
        for (customers c : customers) {
            int id = c.getId();
            String name = c.getName();
            String surname = c.getSurname();
            String city = c.getCity();
            String phone = c.getPhone_number();
            result = result + "\nID: " + id + "\nName: " + name + "\nSurname: " + surname + "\nCity: " + city + "\nPhone number: " + phone + "\n";
        }
        return result;
    }

    //αν πατηθει το update καλω την μεθοδο sendid αλλιως αν πατηθει το delete εκτελω ενα delete ερωτημα οπου εχω εχω το αντικειμο με το id που εβαλε ο χρηστης
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_btn:
                if (!validate_id()) {
                    break;
                }
                sendid();
                break;
            case R.id.delete_btn:
                int userid = 0;
                if (!validate_id()) {
                    break;
                }
                try {
                    userid = Integer.parseInt(id_txt.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Δώσε αριθμό: " + ex);
                }
                customers customer = new customers();
                customer.setId(userid);
                MainActivity.myAppDatabase.myDao().delete_customer(customer);
                customers_view.setText("");
                result = "";
                result = show_customers();
                customers_view.setText(result);
                Toast.makeText(getActivity(), "Επιτυχής διαγραφή", Toast.LENGTH_LONG).show();
                id_txt.setText("");
                break;
        }
    }

    //βαζω στο bundle το id που έδωσε ο χρηστης και καλω με τον fragment manager το update_customer_fragment στελνοντας και το bundle μαζι
    private void sendid() {
        int userid = 0;
        try {
            userid = Integer.parseInt(id_txt.getText().toString());
        } catch (NumberFormatException ex) {
            System.out.println("Δεν έγινε η μετατροπή σωστα " + ex);
        }
        update_customer_fragment update_customer_fragment = new update_customer_fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("userid", userid);
        update_customer_fragment.setArguments(bundle);
        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, update_customer_fragment).addToBackStack(null).commit();
    }


    private boolean validate_id() {
        if (id_txt.getText().toString().trim().isEmpty()) {
            Toast.makeText(getActivity(), "Συμπλήρωσε αριθμό ID", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
