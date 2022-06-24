package com.example.indi_pro3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.indi_pro3.Dialog.Confirm_dialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    MainActivity activity;
    DrawerLayout drawer;
    NavigationView nav_View;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(HomeFragment.newInstance("", ""));

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:
                        loadFragment(HomeFragment.newInstance("", ""));
                        return true;
                    case R.id.search:
                        loadFragment(CartFragment.newInstance("", ""));
                        return true;
                }
                return false;
            }
        });

    }

    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onBackPressed() {
        new Confirm_dialog(this).show("Exit App!", "Are you sure you wan't to exit?", "Yes", "No", new DoInBackGroundCaller() {
            @Override
            public void doTask() {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

            @Override
            public void doTaskComplete() {

            }
        }, new DoInBackGroundCaller() {
            @Override
            public void doTask() {

            }

            @Override
            public void doTaskComplete() {

            }
        });
    }
}