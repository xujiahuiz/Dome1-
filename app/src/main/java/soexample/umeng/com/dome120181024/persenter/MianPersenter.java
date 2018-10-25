package soexample.umeng.com.dome120181024.persenter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;



import java.util.ArrayList;
import java.util.List;
import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.MainActivity;
import soexample.umeng.com.dome120181024.fragment.GDFragment;
import soexample.umeng.com.dome120181024.fragment.ListFragment;
import soexample.umeng.com.dome120181024.fragment.MeFragment;
import soexample.umeng.com.dome120181024.fragment.ShouYeFragment;
import soexample.umeng.com.dome120181024.mvp.view.AppIDetegate;

/**
 * 作者：xujiahui
 * 时间：2018/10/22
 * 作用：MianPersenter
 */
public class MianPersenter extends AppIDetegate {
    private Context mcontext;
    private ViewPager viewpager;
    private TabLayout tablayout;
    private String[] data = {"首页", "我的","高德","列表"};

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShouYeFragment());
        fragments.add(new MeFragment());
        fragments.add(new GDFragment());
        fragments.add(new ListFragment());
        viewpager.setAdapter(new FragmentPagerAdapter(((MainActivity) mcontext).getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return data[position];
            }
        });
        tablayout.setupWithViewPager(viewpager);
    }

    private void initView() {
        viewpager = (ViewPager) get(R.id.main_viewpager);
        tablayout = (TabLayout) get(R.id.mian_tablayout);
    }

    @Override
    public void initContext(Context context) {
        this.mcontext = context;
    }
}
