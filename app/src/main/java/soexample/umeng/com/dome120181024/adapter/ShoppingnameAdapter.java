package soexample.umeng.com.dome120181024.adapter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.bean.RecyBean;
import soexample.umeng.com.dome120181024.bean.ShopBean;
import soexample.umeng.com.dome120181024.net.RecycleAdapter;
import soexample.umeng.com.dome120181024.net.ViewHolder;

public class ShoppingnameAdapter extends RecycleAdapter<ShopBean.DataBean.ListBean> {
    private Context context;
    private EditText num;

    public ShoppingnameAdapter(Context mcontext, List<ShopBean.DataBean.ListBean> datas) {
        super(mcontext, datas);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_shoppingname;
    }

    @Override
    protected void convert(final ViewHolder viewHolder, final ShopBean.DataBean.ListBean listBean, final int postion) {

        viewHolder.setText(R.id.child_name_name, listBean.getName()).setImageUrl(R.id.child_name_pic, listBean.getIcon());

    }

}





