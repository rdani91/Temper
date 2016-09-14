package hu.daniel.rozsa;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import hu.daniel.rozsa.fragment.StartScreenFragment;
import hu.rozsa.daniel.temper.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loadStartScreenFragment();
    }

    private void loadStartScreenFragment() {
        StartScreenFragment startScreenFragment = StartScreenFragment.getInstance();
        loadFragment(startScreenFragment);
    }

    private void loadFragment(Fragment fragmentToLoad) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragmentToLoad)
                .commit();
    }
}
