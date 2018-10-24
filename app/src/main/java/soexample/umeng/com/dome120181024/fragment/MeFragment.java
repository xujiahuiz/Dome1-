package soexample.umeng.com.dome120181024.fragment;


import soexample.umeng.com.dome120181024.mvp.persenter.BaseFragment;
import soexample.umeng.com.dome120181024.persenter.MePersenter;

public class MeFragment extends BaseFragment<MePersenter> {
    @Override
    protected Class<MePersenter> getlayout() {
        return MePersenter.class;
    }

}
