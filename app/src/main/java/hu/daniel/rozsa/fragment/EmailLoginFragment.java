package hu.daniel.rozsa.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import hu.daniel.rozsa.FragmentChanger;
import hu.daniel.rozsa.Logic;
import hu.daniel.rozsa.logic.OnCompleteResult;
import hu.daniel.rozsa.logic.entity.User;
import hu.rozsa.daniel.tender.R;

public class EmailLoginFragment extends LoginFragmentType {
    private FragmentChanger fragmentChanger;
    private TextInputLayout etUserName;
    private TextInputLayout etPassword;
    private View btnLogin, btnRegister, tvFBLogin;

    @Override
    public void setFragmentChangeListener(FragmentChanger listener) {
        this.fragmentChanger = listener;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadUiElements(view);

        btnLogin.setOnClickListener(new LoginButtonOnClickListener());
        view.findViewById(R.id.tvFbLogin)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentChanger.changeFragment();
                }
            });


        view.findViewById(R.id.btnRegister)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "In progress...", Toast.LENGTH_SHORT)
                         .show();
                }
            });
    }

    private void loadUiElements(View view) {
        etPassword = (TextInputLayout) view.findViewById(R.id.etPassword);
        etUserName = (TextInputLayout) view.findViewById(R.id.etUserName);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);
        tvFBLogin = view.findViewById(R.id.tvFbLogin);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_email, container, false);
    }

    private class LoginButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String password = etPassword.getEditText()
                                        .getText()
                                        .toString();
            String userName = etUserName.getEditText()
                                        .getText()
                                        .toString();

            Logic.getInstance()
                 .getUserInteractor()
                 .login(userName, password, new OnCompleteResult<User>() {
                     @Override
                     public void onSuccess(User result) {
                         fragmentChanger.onLoginSuccess(result);

                     }

                     @Override
                     public void onError(String errorMsg) {
                         Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT)
                              .show();
                     }
                 });
        }
    }
}
