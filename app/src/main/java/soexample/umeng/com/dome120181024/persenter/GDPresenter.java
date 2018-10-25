package soexample.umeng.com.dome120181024.persenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import soexample.umeng.com.dome120181024.GDActivity;
import soexample.umeng.com.dome120181024.MainActivity;
import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.mvp.view.AppIDetegate;

public class GDPresenter extends AppIDetegate {
    private Context mcontext;
    private Button btgd;

    @Override
    protected int getLayout() {
        return R.layout.layout_gd_fragment;
    }

    @Override
    public void initData() {
        super.initData();
        btgd = (Button) get(R.id.bt_gd);
        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_gd:
                       mcontext.startActivity(new Intent(((MainActivity) mcontext), GDActivity.class));
                        break;
                }
            }
        }, R.id.bt_gd);

    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mcontext = context;
    }
}
