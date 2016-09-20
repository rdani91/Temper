package hu.daniel.rozsa.binding;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.rozsa.daniel.tender.databinding.FragmentBindingBinding;


public class BindingFragment extends Fragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentBindingBinding binding = FragmentBindingBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        BindingUser user = new BindingUser();
        user.userName = "Pisti";
        binding.setUser(user);

        return rootView;
    }
}
