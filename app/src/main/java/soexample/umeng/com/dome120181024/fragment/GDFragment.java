package soexample.umeng.com.dome120181024.fragment;


import soexample.umeng.com.dome120181024.mvp.persenter.BaseFragment;
import soexample.umeng.com.dome120181024.persenter.GDPresenter;
import soexample.umeng.com.dome120181024.persenter.MePersenter;

public class GDFragment extends BaseFragment<GDPresenter> {

    @Override
    protected Class<GDPresenter> getlayout() {
        return GDPresenter.class;
    }
}
