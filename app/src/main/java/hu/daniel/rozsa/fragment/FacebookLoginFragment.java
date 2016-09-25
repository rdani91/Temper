package hu.daniel.rozsa.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import hu.daniel.rozsa.FragmentChanger;
import hu.rozsa.daniel.tender.R;

public class FacebookLoginFragment extends LoginFragmentType {

    private FragmentChanger fragmentChanger;
    @Override
    public void setFragmentChangeListener(FragmentChanger listener) {
        this.fragmentChanger = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_fb, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tvEmailLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChanger.changeFragment();
            }
        });

        view.findViewById(R.id.btnFbLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Under development", Toast.LENGTH_SHORT)
                     .show();
            }
        });
    }
}
