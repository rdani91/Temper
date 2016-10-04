package hu.daniel.rozsa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import hu.daniel.rozsa.Logic;
import hu.daniel.rozsa.fragment.UserDetailScreen;
import hu.daniel.rozsa.fragment.UserListFragment;
import hu.daniel.rozsa.viewpager.ViewPagerExampleFragment;
import hu.daniel.rozsa.logic.entity.User;
import hu.rozsa.daniel.tender.R;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_LOGIN = 1001;

    private DrawerLayout leftDrawer;
    private Toolbar toolbar;
    private TextView tvUserName, tvUserLocation, tvUserEmail;
    private NavigationView drawerListView;

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

        drawerListView.setNavigationItemSelectedListener(new MyNavigationListener());

        drawerToggle.syncState();

        leftDrawer.addDrawerListener(drawerToggle);

    }


    private void loadUiElements() {
        leftDrawer = (DrawerLayout) findViewById(R.id.leftDrawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerListView = (NavigationView) findViewById(R.id.lvDrawer);
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
            if (getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof UserListFragment) {
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

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
                    tvUserLocation.setText(loggedInUser.location);

                    drawerListView.getMenu()
                                  .getItem(0)
                                  .setChecked(true);
                    loadFragment(UserListFragment.newInstance());
                    break;
                case RESULT_CANCELED:
                    finish();
            }
        }

    }

    private void loadFragment(Fragment f, boolean needToAddToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_enter,
                                     R.anim.fragment_exit,
                                     R.anim.fragment_enter_pop,
                                     R.anim.fragment_exit_pop)
                .replace(R.id.fragmentContainer, f);

        if (needToAddToBackStack) {
            transaction.addToBackStack(f.toString());
        }

        transaction.commit();
    }

    private void loadFragment(Fragment f) {
        loadFragment(f, true);

    }

    public void moveToUserDetailScreen(User selectedUser) {
        loadFragment(UserDetailScreen.newInstance(selectedUser));
    }

    private class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            leftDrawer.closeDrawers();

            Menu menu = drawerListView.getMenu();
            for (int i = 0; i < menu.size(); i++) {
                MenuItem currentItem = menu.getItem(i);
                if(!currentItem.equals(item)){
                    currentItem.setChecked(false);
                }
            }
            if (item.isChecked()) {
                return true;
            } else {
                item.setChecked(true);
            }

            switch (item.getItemId()) {
                case R.id.listView:
                    drawerListView.setCheckedItem(item.getItemId());
                    loadFragment(UserListFragment.newInstance());
                    return true;

                case R.id.viewPager:
                    drawerListView.setCheckedItem(item.getItemId());
                    loadFragment(ViewPagerExampleFragment.newInstance());
                    return true;
            }

            return true;
        }
    }
}
