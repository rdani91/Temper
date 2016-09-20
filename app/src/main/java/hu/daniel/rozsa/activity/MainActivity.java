package hu.daniel.rozsa.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import hu.daniel.rozsa.Logic;
import hu.daniel.rozsa.binding.BindingFragment;
import hu.rozsa.daniel.tender.R;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_LOGIN = 1001;

    private DrawerLayout leftDrawer;
    private NavigationView drawerListView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Logic.getInstance()
                 .getUserInteractor()
                 .needToLogin()) {
            startActivityForResult(new Intent(this, LoginActivity.class), REQUEST_CODE_LOGIN);
        }

        setContentView(R.layout.activity_main);

        loadUiElements();
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, leftDrawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerToggle.syncState();

        leftDrawer.addDrawerListener(drawerToggle);

        loadFragment(new BindingFragment());
    }


    private void loadUiElements() {
        leftDrawer = (DrawerLayout) findViewById(R.id.leftDrawer);
        drawerListView = (NavigationView) findViewById(R.id.lvDrawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void onBackPressed() {
        if (leftDrawer.isDrawerOpen(GravityCompat.START)) {
            leftDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_LOGIN) {
            switch (resultCode) {
                case RESULT_OK:
                    //User data accessible after this
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT)
                         .show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT)
                         .show();

            }
        }

    }

    private void loadFragment(Fragment f) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, f)
                .commit();
    }
}
