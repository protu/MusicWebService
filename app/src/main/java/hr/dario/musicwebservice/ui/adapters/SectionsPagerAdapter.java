package hr.dario.musicwebservice.ui.adapters;


import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hr.dario.musicwebservice.R;
import hr.dario.musicwebservice.ui.fragments.PlayListFragment;
import hr.dario.musicwebservice.ui.fragments.RecordFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return RecordFragment.newInstance();
        } else if (position == 1) {
            return PlayListFragment.newInstance();
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.search);
            case 1:
                return context.getString(R.string.playlist);
        }
        return null;
    }
}
