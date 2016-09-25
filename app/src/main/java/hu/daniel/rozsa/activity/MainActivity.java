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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import hu.daniel.rozsa.Logic;
import hu.daniel.rozsa.fragment.UserListFragment;
import hu.daniel.rozsa.logic.entity.User;
import hu.rozsa.daniel.tender.R;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_LOGIN = 1001;

    private DrawerLayout leftDrawer;
    private Toolbar toolbar;
    private TextView tvUserName, tvUserLocation, tvUserEmail;

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

    }


    private void loadUiElements() {
        leftDrawer = (DrawerLayout) findViewById(R.id.leftDrawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView drawerListView = (NavigationView) findViewById(R.id.lvDrawer);
        View headerView = drawerListView.getHeaderView(0);

        tvUserEmail = (TextView) headerView.findViewById(R.id.tvEmail);
        tvUserLocation = (TextView) headerView.findViewById(R.id.tvLocation);
        tvUserName = (TextView) headerView.findViewById(R.id.tvUserName);
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

                    User loggedInUser = (User) data.getSerializableExtra(LoginActivity.EXTRA_USER);
                    tvUserName.setText(loggedInUser.name);
                    tvUserEmail.setText(loggedInUser.e_mail);

                    loadFragment(UserListFragment.newInstance());
                    break;
                case RESULT_CANCELED:
                    finish();
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
