package soexample.umeng.com.dome120181024.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import soexample.umeng.com.dome120181024.mvp.view.AppIDetegate;

/**
 * 作者：xujiahui
 * 时间：2018/10/22
 * 作用：BaseFragment(Franment父类)
 */
public abstract class BaseFragment<T extends AppIDetegate> extends Fragment {
    private T appIDetegate;

    public BaseFragment() {
        try {
            appIDetegate = getlayout().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

    }

    protected abstract Class<T> getlayout();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        appIDetegate.create(getLayoutInflater(), null, savedInstanceState);
        return appIDetegate.rooyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appIDetegate.initContext(getActivity());
        appIDetegate.initData();
    }
}
