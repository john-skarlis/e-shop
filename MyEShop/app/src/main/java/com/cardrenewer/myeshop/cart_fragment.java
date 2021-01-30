package com.cardrenewer.myeshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class cart_fragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Recycle_cart_adapter adapter;
    private List<cart_item_result> cart_list= null;
    Button clear_btn, final_add_btn;

    public cart_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cart_fragment_layout, container, false);

        //αντιστοιχιση στοιχειων
        recyclerView = view.findViewById(R.id.cart_recycler);
        clear_btn = view.findViewById(R.id.clear_btn);
        final_add_btn = view.findViewById(R.id.final_sale_btn);
        //ακροατες στα κουμπια
        clear_btn.setOnClickListener(this);
        final_add_btn.setOnClickListener(this);
        //παιρνουμε τα πεδία που θελουμε για την προβολη του καλαθιου
        cart_list = MainActivity.myAppDatabase.myDao().get_cart();
        //δημιουργεια ανταπτορα για το καλαθι
        adapter = new Recycle_cart_adapter(cart_list);
        //οριζω το layout που θελω για το recycler view
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }

    //μεθοδος για τον χειρισμο του κλικ
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_btn:
                //ελεγχος εαν το καλαθι ειναι αδειο
                if (cart_list == null || cart_list.isEmpty()){
                    Toast.makeText(getActivity(), "Δεν υπάρχει τίποτα στο καλάθι", Toast.LENGTH_LONG).show();
                    break;
                }else {
                    //ερωτημα εκκαθαρισης του πινακα cart
                    MainActivity.myAppDatabase.myDao().clear_cart();
                    //ξαναφορτωνω το ιδιο fragment για να ανανεωθει αυτο που βλεπω
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new cart_fragment()).addToBackStack(null).commit();
                }
                break;
            case R.id.final_sale_btn:
                //ελεγχος εαν το καλαθι ειναι αδειο
                if (cart_list==null || cart_list.isEmpty()){
                    Toast.makeText(getActivity(), "Δεν υπάρχει τίποτα στο καλάθι", Toast.LENGTH_LONG).show();
                    break;
                }else {
                    //οριζω ενα flag για να το κανω true αν καποια απο τις παραγγελιες του καλαθιου δεν καταχωρηθει επιτυχος
                    boolean flag = false;
                    Date date = null;
                    //μεθοδο επαναληψης για καταχωρηση του καλαθιου στον πινακα sales για οριστικη παραγγελια
                    for (cart_item_result c : cart_list) {
                        sales sale = new sales();
                        sale.setCid(c.getCart_cust_id());
                        sale.setPid(c.getCart_prod_id());
                        sale.setSale_quantity(c.getCart_quantity());
                        //παιρνω την ημερομηνια και την ωρα του συστηματος την ωρα της παραγγελιας
                        date = Calendar.getInstance().getTime();
                        sale.setDate(date.toString());

                        //παιρνω την ποσοτητα του προιοντος και το κανω ελεγχο ξανα αν ειναι επαρκης η ποσοστητα που ζητηται και αν ναι κανω την αφεραιρεση για να μεινει το υπολοιπο
                        //και προχωραω στην οριστικοποιηση της παραγγελιας αλλιως θετω true το φλαγκ που σημαινει οτι πηγε λαθος η συγκεκριμενη παραγγελια
                        products product = MainActivity.myAppDatabase.myDao().get_product(c.getCart_prod_id());
                        int final_quantity = 0;
                        final_quantity = product.getQuantity() - c.getCart_quantity();
                        if (final_quantity >= 0) {
                            MainActivity.myAppDatabase.myDao().update_quantity(final_quantity, product.getId());
                            MainActivity.myAppDatabase.myDao().add_sales(sale);
                        } else {
                            flag = true;
                        }
                    }
                    //εμφανιζω αν το πηγε καποια παραγγελια λαθος η αν μπηκαν ολες ενα αναλογο μηνυμα
                    if (flag) {
                        Toast.makeText(getActivity(), "Κάποια πραγγέλία δεν καταχωρήθηκε λόγο ανεπαρκής ποσότητας", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Επιτυχής ολοκλήρωση", Toast.LENGTH_LONG).show();
                    }
                    //καθαρσμος και ανανεωση σε αυτο που βλεπω
                    MainActivity.myAppDatabase.myDao().clear_cart();
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new cart_fragment()).addToBackStack(null).commit();
                }
                break;

        }
    }
}
