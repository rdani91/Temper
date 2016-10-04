package hu.daniel.rozsa.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TestPagerAdapter extends FragmentPagerAdapter {

    public TestPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int offset = position % 4;
        Fragment f;
        switch (offset) {
            case 0:
                f = new Fragment1();
                break;

            case 1:
                f = new Fragment2();
                break;

            case 2:
                f = new Fragment3();
                break;

            case 3:
                f = new Fragment4();

                break;

            default:
                f = new Fragment1();
        }

        return f;
    }

    @Override
    public int getCount() {
        return 100;
    }
}
