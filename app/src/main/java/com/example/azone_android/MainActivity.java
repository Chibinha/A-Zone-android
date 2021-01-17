package com.example.azone_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = new ProductListFragment();
        setTitle("A+Zone");
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();

        // TODO: Show user data on header
    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_login:
                Intent login_intent = new Intent(this, LoginActivity.class);
                startActivity(login_intent);
        }

        if(fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}