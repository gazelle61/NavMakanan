package com.example.navmakanan;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import fragments.HomeFragment;
import fragments.MakananFav;
import fragments.MakananKhas;
import fragments.MinumanKhas;
import fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView; // Moved the declaration here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Setting the toolbar as action bar

        showHomePage();

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navigationView = findViewById(R.id.nav_view); // Moved here
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_item1) {
                    showHomePage();
                } else if (itemId == R.id.menu_item2) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, new MakananKhas())
                            .commit();
                    getSupportActionBar().setTitle("Makanan Khas");
                } else if (itemId == R.id.menu_item3) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, new MinumanKhas())
                            .commit();
                    getSupportActionBar().setTitle("Minuman Khas");
                }else if (itemId == R.id.menu_item4) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, new MakananFav())
                            .commit();
                    getSupportActionBar().setTitle("Makanan Favorit");
                }else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, new ProfileFragment())
                            .commit();
                    getSupportActionBar().setTitle("Profile Page");
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    private void showHomePage() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new HomeFragment())
                .commit();

        getSupportActionBar().setTitle("Home Page");
    }
}
