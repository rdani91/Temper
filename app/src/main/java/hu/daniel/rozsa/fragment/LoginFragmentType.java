package hu.daniel.rozsa.fragment;

import android.app.Fragment;

import hu.daniel.rozsa.FragmentChanger;

public abstract class LoginFragmentType extends Fragment {

    public abstract void setFragmentChangeListener(FragmentChanger listener);
}
