package hu.daniel.rozsa.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import hu.daniel.rozsa.fragment.UserListFragment;
import hu.rozsa.daniel.tender.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loadFragment(UserListFragment.newInstance());
    }


    private void loadFragment(Fragment f){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, f)
                .commit();
    }
}
