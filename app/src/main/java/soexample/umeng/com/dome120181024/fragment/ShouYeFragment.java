package soexample.umeng.com.dome120181024.fragment;



import soexample.umeng.com.dome120181024.mvp.persenter.BaseFragment;
import soexample.umeng.com.dome120181024.persenter.ShouYePersenter;

public class ShouYeFragment extends BaseFragment<ShouYePersenter> {
    @Override
    protected Class<ShouYePersenter> getlayout() {
        return ShouYePersenter.class;
    }
}
