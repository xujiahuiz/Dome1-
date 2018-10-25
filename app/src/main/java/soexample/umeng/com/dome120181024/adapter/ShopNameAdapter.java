package soexample.umeng.com.dome120181024.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.bean.RecyBean;
import soexample.umeng.com.dome120181024.bean.ShopBean;
import soexample.umeng.com.dome120181024.net.RecycleAdapter;
import soexample.umeng.com.dome120181024.net.ViewHolder;

public class ShopNameAdapter extends RecycleAdapter<ShopBean.DataBean> {
    private Context context;
    private ShoppingAdapter.OkClick click;

    public void setClick(ShoppingAdapter.OkClick click) {

        this.click = click;
    }

    public ShopNameAdapter(Context mcontext, List<ShopBean.DataBean> datas) {
        super(mcontext, datas);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shop_name;

    }

    @Override
    protected void convert(ViewHolder viewHolder, ShopBean.DataBean dataBean, int postion) {
        viewHolder.setText(R.id.shopname_name, dataBean.getName());
        RecyclerView recyShop = (RecyclerView) viewHolder.getView(R.id.shopname_shop);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyShop.setLayoutManager(staggeredGridLayoutManager);
        ShoppingnameAdapter shoppingAdapter = new ShoppingnameAdapter(context,dataBean.getList());

        recyShop.setAdapter(shoppingAdapter);

    }
}
