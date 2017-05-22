package br.com.fiap.sub.burguerreview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Navigation extends AppCompatActivity {

    String username ;
    String password ;

    TextView tv_user;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    Bundle bundle;

    Fragment fragment ;

    android.support.v4.app.FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);


        initialize();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menu_register :
                        bundle = new Bundle();
                        bundle.putString("Action", "register");

                        fragment = new Register();
                        fragment.setArguments(bundle);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();
                        return true;

                    case R.id.menu_review :
                        bundle = new Bundle();
                        bundle.putString("Action", "update");

                        fragment = new HamburgerHouseRecycleView();
                        fragment.setArguments(bundle);

                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();
                        return true;

                    case R.id.menu_about :
                        Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();
                        About fragment = new About();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();
                  return true;

                    case R.id.menu_exit :
                        Intent intent = new Intent(Navigation.this, SplashScreen.class);
                        intent.putExtra("status","close");
                        startActivity(intent);
                        return true;


                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        drawer.openDrawer(Gravity.LEFT);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    private void initialize(){

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        tv_user = (TextView) findViewById(R.id.tv_user_header);

        sharedPreference();
    }

    private void sharedPreference(){
        SharedPreferences sharedPref = getSharedPreferences("burguerReview", Context.MODE_PRIVATE);
        username = sharedPref.getString("USERNAME","");
        password = sharedPref.getString("PASSWORD","");
    }
}
