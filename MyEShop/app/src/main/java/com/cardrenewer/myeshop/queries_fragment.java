package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class queries_fragment extends Fragment {

    Spinner spinner;
    TextView querytextview, querytextresult;
    ArrayAdapter<CharSequence> adapter;
    Button btnqueryresult;
    int test;

    public queries_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.queries_fragment_layout, container, false);

        //παιρνω τον πινακα με τις περιγραφες
        final String[] querry = getResources().getStringArray(R.array.queries_description_array);
        //αντιστοιχιζω τα στοιχεια και βαζω στο σπινερ εναν ανταπτορα με τα νουμερα απο τα ερωτηματα
        querytextview = view.findViewById(R.id.textquery);
        spinner = view.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.queries_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //εμφανιζω τηνν περιγραφη στο text view και ανεβαζω την μεταβλητη position + 1 για να ελεγξω το case
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                querytextview.setText(querry[position]);
                test = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        querytextresult = view.findViewById(R.id.txtqueryresult);
        btnqueryresult = view.findViewById(R.id.queryrun);
        btnqueryresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querytextresult.setText("test" + test);
                String result = "";
                switch (test) {
                    //παιρνω τα προϊόντα και εμφανιζω το όνομα και την διαθεσιμη ποσοτητα
                    case 1:
                        List<products> product = MainActivity.myAppDatabase.myDao().get_products();
                        for (products p : product) {
                            int id = p.getId();
                            String name = p.getName();
                            int quantity = p.getQuantity();
                            result = result + "\nID: " + id + "\nΠροϊόν: " + name + "\nΔιαθέσιμη ποσότητα: " + quantity + "\n";
                        }
                        querytextresult.setText(result);
                        break;
                    //παιρνω τις συνολικες πολησεις και τις εμφανιζω
                    case 2:
                        int total_sales = MainActivity.myAppDatabase.myDao().get_total_sales();
                        result = "Η συνολικές πωλήσεις είναι: " + total_sales;
                        querytextresult.setText(result);
                        break;
                    //παιρνω τα συνολικα κομματια πυ πωλήθηκαν απο καθε προϊόν
                    case 3:
                        List<query3> query3 = MainActivity.myAppDatabase.myDao().get_product_sale();
                        for (query3 q : query3) {
                            String name = q.getProduct_name();
                            int sales = q.getProduct_sales();
                            result = result + "\nΌνομα: " + name + "\nΠωλήσεις: " + sales + "\n";
                        }
                        querytextresult.setText(result);
                        break;
                    //παιρνω τα στοιχεια το καθε πελατη και τις συνολικες του πωλησεις
                    case 4:
                        List<query4> query4 = MainActivity.myAppDatabase.myDao().get_customers_orders();
                        for (query4 q : query4) {
                            String name = q.getName();
                            String surname = q.getSurname();
                            String city = q.getCity();
                            int orders = q.getOrders();
                            result = result + "\nΌνομα: " + name + "\nΕπίθετο: " + surname + "\nΠόλη: " + city + "\nΣυνολικές παραγγελίες: " + orders + "\n";
                        }
                        querytextresult.setText(result);

                        break;
                    //παιρνω και εμφανιζω τα σημαντικα στοιχεια καθε προϊόντος και τα συνολικα κομματια που πωλήθηκαν και τα συνολικα εσοδα απο οσα πωλήθηκαν
                    case 5:
                        List<query5> query5 = MainActivity.myAppDatabase.myDao().get_products_info();
                        for (query5 q : query5) {
                            String name = q.getName();
                            String cat_name = q.getCat_name();
                            Float price = q.getPrice();
                            int orders = q.getQuantity();
                            result = result + "\nΌνομα προϊόντος: " + name + "\nΚατηγορία προϊόντος: " + cat_name + "\nΣυνολικά έσοδα: " + price + "€" +
                                    "\nΣυνολικά τεμάχια που πουλήθηκαν: " + orders + "\n";
                        }
                        querytextresult.setText(result);

                        break;
                }
            }
        });

        return view;
    }

}
