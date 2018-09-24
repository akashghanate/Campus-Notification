package com.example.abhinandan.onlinedb;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class Teacherlayout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        /*start frament(deafult)
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new quick_message()).commit();
            navigationView.setCheckedItem(R.id.nav_msg);
        }*/
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_details:
                Toast.makeText(this,"Details",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_studentdatabase:
                Toast.makeText(this,"Database",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new about()).commit();
                break;
            case R.id.nav_add:
                Toast.makeText(this,"Add circular",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_remove:
                Toast.makeText(this,"Delete Circular",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_msg:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new quick_message()).commit();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}