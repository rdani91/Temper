package hu.daniel.rozsa.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import hu.daniel.rozsa.FragmentChanger;
import hu.daniel.rozsa.fragment.EmailLoginFragment;
import hu.daniel.rozsa.fragment.FacebookLoginFragment;
import hu.daniel.rozsa.fragment.LoginFragmentType;
import hu.daniel.rozsa.logic.entity.User;
import hu.rozsa.daniel.tender.R;

public class LoginActivity extends AppCompatActivity implements FragmentChanger {
    public static final String EXTRA_USER = "extra_user";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loadFragmentWithFlipAnimation(new FacebookLoginFragment());
    }

    private void loadFragmentWithFlipAnimation(LoginFragmentType targetFragment) {
        targetFragment.setFragmentChangeListener(this);
        getFragmentManager()

                .beginTransaction()
                .setCustomAnimations(R.animator.card_flip_right_in,
                                     R.animator.card_flip_right_out,
                                     R.animator.card_flip_left_in,
                                     R.animator.card_flip_left_out)
                .replace(R.id.loginFormContainer, targetFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void changeFragment() {
        Fragment currentLoadedFragment = getFragmentManager().findFragmentById(R.id.loginFormContainer);
        boolean isFbShown = currentLoadedFragment instanceof FacebookLoginFragment;
        loadFragmentWithFlipAnimation(isFbShown? new EmailLoginFragment() : new FacebookLoginFragment());
    }

    @Override
    public void onLoginSuccess(User loggedInUser) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_USER, loggedInUser);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
