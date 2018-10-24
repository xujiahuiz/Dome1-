package soexample.umeng.com.dome120181024.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：xujiahui
 * 时间：2018/10/22
 * 作用：IDetegate(接口)
 */
public interface IDetegate {
    //初始数据
    void initData();

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    View rooyView();

    void initContext(Context context);
}
