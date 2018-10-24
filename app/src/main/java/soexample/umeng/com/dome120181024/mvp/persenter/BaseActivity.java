package soexample.umeng.com.dome120181024.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import soexample.umeng.com.dome120181024.mvp.view.AppIDetegate;

/**
 * 作者：xujiahui
 * 时间：2018/10/22
 * 作用：BaseActivity(activity父类)
 */
public abstract class BaseActivity<T extends AppIDetegate> extends AppCompatActivity {
    private T appIDetegate;

    public BaseActivity() {
        try {
            appIDetegate = getlayout().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected abstract Class<T> getlayout();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appIDetegate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(appIDetegate.rooyView());
        appIDetegate.initContext(this);
        appIDetegate.initData();

    }
}
