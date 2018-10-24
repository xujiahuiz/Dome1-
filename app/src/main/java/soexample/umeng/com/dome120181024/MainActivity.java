package soexample.umeng.com.dome120181024;


import soexample.umeng.com.dome120181024.mvp.persenter.BaseActivity;
import soexample.umeng.com.dome120181024.persenter.MianPersenter;

public class MainActivity extends BaseActivity<MianPersenter> {
    @Override
    protected Class<MianPersenter> getlayout() {
        return MianPersenter.class;
    }
}

