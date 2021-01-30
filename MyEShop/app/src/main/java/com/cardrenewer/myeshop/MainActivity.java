package com.cardrenewer.myeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static FragmentManager fragmentManager;
    public static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"shopDB").allowMainThreadQueries().build();
        drawerLayout = findViewById(R.id.drawer_layout);


        //arxikopoihsh tou toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //dimiourgeia toggler gia to eikonidio menu panw δηλαδη να ανοιγει το μενου με το κουμπι το πανω
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //αρχικοποιουμε το fragment manager
        fragmentManager = getSupportFragmentManager();
        //ορίζουμε to navigation view και βάζουμε listeners για κλικ σε καποιο item και και backstack listener για το πισω
        navigationView = findViewById(R.id.NavView);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager.addOnBackStackChangedListener(this);
        //arxikopoioyme to fragment pou theloume mesa sto frame layout otan xekinaei h efarmogh kai to navigation item check
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }


    }

    // se periptwsh pou pathsei o xrhsths to back enw einai anoixtos o drawer na kleisei
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    //elegxoume to klik sto menu etsi wste na metakinhsoyme to item check kai na feroyme to analogo fragment mesa sto framelayout
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (findViewById(R.id.fragment_container) != null) {

            switch (menuItem.getItemId()) {
                case R.id.home:
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new home_fragment()).addToBackStack(null).commit();
                    navigationView.setCheckedItem(R.id.home);
                    break;
                case R.id.cart:
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new cart_fragment()).addToBackStack(null).commit();
                    navigationView.setCheckedItem(R.id.cart);
                    break;
                case R.id.manage:
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new manage_fragment()).addToBackStack(null).commit();
                    navigationView.setCheckedItem(R.id.manage);
                    break;
                case R.id.contact:
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new contact_fragment()).addToBackStack(null).commit();
                    navigationView.setCheckedItem(R.id.contact);
                    break;
                case R.id.exit:
                    finish();
                    System.exit(0);
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return false;
    }

    //methodo backstack gia na elegxoyme to fragment wste otan patane to pisw na metakinhte kai to navigation
    @Override
    public void onBackStackChanged() {
        Fragment current;
        current = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (current instanceof home_fragment) {
            navigationView.setCheckedItem(R.id.home);
        } else if (current instanceof cart_fragment) {
            navigationView.setCheckedItem(R.id.cart);
        } else if (current instanceof contact_fragment) {
            navigationView.setCheckedItem(R.id.contact);
        }else if (current instanceof manage_fragment){
            navigationView.setCheckedItem(R.id.manage);
        }else if(current instanceof search_products_fragment){
            navigationView.setCheckedItem(R.id.home);
        }else if(current instanceof full_view_product){
            navigationView.setCheckedItem(R.id.home);
        }
    }
}
