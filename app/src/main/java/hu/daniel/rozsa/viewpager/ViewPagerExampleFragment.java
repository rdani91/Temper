package hu.daniel.rozsa.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.rozsa.daniel.tender.R;

public class ViewPagerExampleFragment extends Fragment {
    private ViewPager viewPager;

    public static Fragment newInstance() {
        return new ViewPagerExampleFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewpager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new TestPagerAdapter(getChildFragmentManager()));
    }

    public void jumpForward() {
        int currentItem = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 10);

    }
}
