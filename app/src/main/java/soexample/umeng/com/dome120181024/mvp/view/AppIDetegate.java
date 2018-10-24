package soexample.umeng.com.dome120181024.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：xujiahui
 * 时间：2018/10/22
 * 作用：AppIDetegate(实现类)
 */
public abstract class AppIDetegate implements IDetegate {
    private View rootView;
    private Context mcontext;
    private SparseArray<View> views = new SparseArray<>();

    @Override
    public void initData() {

    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayout(), null, false);
    }

    protected abstract int getLayout();

    public <T extends View> T get(int id) {
        T view = (T) views.get(id);
        if (view == null) {
            view = rootView.findViewById(id);
            views.put(id, view);
        }
        return view;
    }

    public void setClick(View.OnClickListener clickListener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            rootView.findViewById(id).setOnClickListener(clickListener);
        }
    }

    @Override
    public View rooyView() {
        return rootView;
    }

    @Override
    public void initContext(Context context) {
        this.mcontext = context;
    }
}
