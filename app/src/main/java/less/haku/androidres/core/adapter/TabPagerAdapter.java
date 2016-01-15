package less.haku.androidres.core.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import less.haku.androidres.core.fragment.DemoFragment;

/**
 * Created by HaKu on 16/1/14.
 * 用于展示TabViewPager
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
    public final int COUNT = 3;
    private String[] titles = new String[]{"HitoKoto", "新番", "useless"};
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new DemoFragment();
//        switch (position) {
//            case 0 :
//                return new HitoKotoFragment();
//            case 1 :
//                return new BanGumiSearchFragment();
//            case 2 :
//                return new BangumiFragment();
//        }
//        return null;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}