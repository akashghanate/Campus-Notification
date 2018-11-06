package com.example.abhinandan.onlinedb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.abhinandan.onlinedb.fragments.about;
import com.example.abhinandan.onlinedb.fragments.add_circular;
import com.example.abhinandan.onlinedb.fragments.circular_staff;
import com.example.abhinandan.onlinedb.fragments.details_staff;
import com.example.abhinandan.onlinedb.fragments.quick_message;
import com.example.abhinandan.onlinedb.fragments.delete_circular;
import com.example.abhinandan.onlinedb.fragments.student_database;

public class Teacherlayout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    SharedPreferences sp;



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

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new circular_staff()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_details:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new details_staff()).commit();
                break;
            case R.id.nav_studentdatabase:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new student_database()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new about()).commit();
                break;
            case R.id.nav_add:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new add_circular()).commit();
                break;
            case R.id.nav_remove:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new delete_circular()).commit();
                break;
            case R.id.nav_msg:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new quick_message()).commit();
                break;
            case R.id.nav_circular_staff:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new circular_staff()).commit();
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
            case R.id.logout:
                sp = getSharedPreferences("MY_SHARE",MODE_PRIVATE);
                sp.edit().putBoolean("IsLogged",false).apply();
                sp.edit().putBoolean("IsStudent",false).apply();
                sp.edit().commit();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_logout,menu);
        return super.onCreateOptionsMenu(menu);
    }
}