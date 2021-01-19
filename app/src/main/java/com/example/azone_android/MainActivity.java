package com.example.azone_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import com.example.azone_android.models.Category;
import com.example.azone_android.models.SingletonStore;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer);
        menu = navigationView.getMenu();

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
    protected void onResume() {
        super.onResume();

        menu.removeGroup(R.string.nav_categories);

        SingletonStore.getInstance(getApplicationContext()).getAllCategoriesAPI(getApplicationContext(), SingletonStore.isConnectedInternet(getApplicationContext()));
        ArrayList<Category> categoryList = SingletonStore.getInstance(getApplicationContext()).getCategoriesDB();
        Menu categoriesSubMenu = menu.addSubMenu(R.string.nav_categories, Menu.NONE, 0, R.string.nav_categories);
        for (final Category category: categoryList) {
            categoriesSubMenu.add(Menu.NONE, category.getId(), Menu.NONE,category.getName()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    return false;
                }
            });
        }
        navigationView.invalidate();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_login:
                Intent login_intent = new Intent(this, LoginActivity.class);
                startActivity(login_intent);
                break;
            case R.id.nav_signup:
                Intent signup_intent = new Intent(this, SignUpActivity.class);
                startActivity(signup_intent);
                break;
            case R.id.nav_settings:
                Intent settings_intent = new Intent(this, SettingsActivity.class);
                startActivity(settings_intent);
                break;
        }

        if(fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}