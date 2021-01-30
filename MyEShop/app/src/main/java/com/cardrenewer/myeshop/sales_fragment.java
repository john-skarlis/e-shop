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
public class sales_fragment extends Fragment implements View.OnClickListener {

    TextView sales_view;
    EditText sale_txt;
    Button update, delete;
    String result = "";

    public sales_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sales_fragment_layout, container, false);

        //αντιστοιχιζω τα στοιχεια
        sales_view = view.findViewById(R.id.show_sales_view);
        sale_txt = view.findViewById(R.id.id_sale_holder);
        update = view.findViewById(R.id.update_s_btn);
        delete = view.findViewById(R.id.delete_s_btn);

        //καλω την μεθοδο show_sales οπου τραβαει ολες τις εγγραφες του πινακα sales και τις εμφανιζει
        result = show_sales();
        sales_view.setText(result);

        //ακροατες για τα κουμπια
        update.setOnClickListener(this);
        delete.setOnClickListener(this);


        return view;
    }

    //αν πατηθει το update καλω την μεθοδο send αλλιως αν πατηθει το delete εκτελω ενα delete ερωτημα οπου εχω εχω το αντικειμο με το id που εβαλε ο χρηστης
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_s_btn:
                if (!validate_id(sale_txt)) {
                    break;
                }
                send();
                break;
            case R.id.delete_s_btn:
                int saleid = 0;
                if (!validate_id(sale_txt)) {
                    break;
                }
                try {
                    saleid = Integer.parseInt(sale_txt.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Δεν έγινε η μετατροπή σωστα " + ex);
                }

                sales sale = new sales();
                sale.setId(saleid);
                MainActivity.myAppDatabase.myDao().delete_sale(sale);
                sales_view.setText("");
                result = "";
                result = show_sales();
                sales_view.setText(result);
                Toast.makeText(getActivity(), "Επιτυχής διαγραφή", Toast.LENGTH_LONG).show();
                sale_txt.setText("");
        }
    }
    //εμφανιζει ολες τις πωλησεις με μια for που κανει στην λιστα salesList
    private String show_sales() {
        List<sales> salesList = MainActivity.myAppDatabase.myDao().get_sales();
        String result = "";
        for (sales s : salesList) {
            int sale_id = s.getId();
            int customer_id = s.getCid();
            int product_id = s.getPid();
            int quantity = s.getSale_quantity();
            String s_date = s.getDate();
            result = result + "\nID Παραγγελίας: " + sale_id + "\nID Πελάτη: " + customer_id + "\nID Προϊόντος: " + product_id + "\nΠοσότητα αγοράς: " + quantity + "\nΗμερομινία αγοράς: " + s_date + "\n";
        }
        return result;
    }

    //βαζω στο bundle το id που έδωσε ο χρηστης και καλω με τον fragment manager το update_sales_fragment στελνοντας και το bundle μαζι
    private void send() {
        int sid = 0;
        try {
            sid = Integer.parseInt(sale_txt.getText().toString());
        } catch (NumberFormatException ex) {
            System.out.println("Δεν έγινε η μετατροπή σωστα " + ex);
        }

        update_sales_fragment update_sales_fragment = new update_sales_fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("sid", sid);
        update_sales_fragment.setArguments(bundle);
        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, update_sales_fragment).addToBackStack(null).commit();
    }


    private boolean validate_id(EditText t) {
        if (t.getText().toString().trim().isEmpty()) {
            Toast.makeText(getActivity(), "Το πεδίο είναι απαραίτητο", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}

