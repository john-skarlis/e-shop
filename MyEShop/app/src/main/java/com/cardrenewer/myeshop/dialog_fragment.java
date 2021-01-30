package com.cardrenewer.myeshop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.List;

public class dialog_fragment extends AppCompatDialogFragment {

    List<customers> customers;
    EditText quantity_edit;
    Spinner customer_spin;
    customers customer;
    int quantity = 0;
    int avaliable_qunatity = 0;
    int prod_id = 0;

    public dialog_fragment(List<customers> customers, int avaliable_qunatity, int prod_id) {
        //φερνω με τον δομητη τρεις παραμτρους 1)τους πελατες για το σπινερ 2)την διαθεσιμη ποσοτητα 3)το id του προιοντος
        this.customers = customers;
        this.avaliable_qunatity = avaliable_qunatity;
        this.prod_id = prod_id;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //διμιουργω εναν inflater για να το κανω inflate
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.buy_dialog_layout, null);

        //αντιστοιχιζω τα στοιχεια μου
        customer_spin = view.findViewById(R.id.user_spinner);
        quantity_edit = view.findViewById(R.id.dialog_quantity);

        //αρχικοποιω τον ανταπτορα και τον βαζω στο spinner
        ArrayAdapter<customers> adapter = new ArrayAdapter<customers>(getActivity(), android.R.layout.simple_spinner_item, customers);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        customer_spin.setAdapter(adapter);

        //δημιουργω στην διεπαφη το dialog εναν τιτλο και δυο κουμπια με τις λειτουργίες τους οταν πατηθουν
        //στο κουμπι αρνησης απλα κλεινει χωρις να κανει τιποτα
        //στο θετικο κουμπι παιρνει το χρηστη του σπινερ και την ποσοτητα που εβαλε και ελεγχει με αυτη του δομητη αν μπορει να προχωρησει κσι καταχωρει την παραγγελια στον καλαθι
        builder.setView(view)
                .setTitle("Στοιχεία παραγγελίας")
                .setNegativeButton("Ακυρωση", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton("Προσθηκη", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        customer = (customers) customer_spin.getSelectedItem();
                        quantity = Integer.parseInt(quantity_edit.getText().toString());
                        if ((avaliable_qunatity - quantity) >= 0) {
                            cart cart = new cart();
                            cart.setCust_id(customer.getId());
                            cart.setProd_id(prod_id);
                            cart.setQuantity(quantity);
                            MainActivity.myAppDatabase.myDao().add_to_cart(cart);
                            Toast.makeText(getActivity(), "Προστέθηκε στο καλάθι", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Μη έγκυρη ποσότητα", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return builder.create();
    }
}
