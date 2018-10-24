package soexample.umeng.com.dome120181024.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.bean.RecyBean;
import soexample.umeng.com.dome120181024.net.RecycleAdapter;
import soexample.umeng.com.dome120181024.net.ViewHolder;

public class ShopCarAdapter extends RecycleAdapter<RecyBean.DataBean> {
    private Context context;
    private ShoppingAdapter.OkClick click;

    public void setClick(ShoppingAdapter.OkClick click) {

        this.click = click;
    }

    public ShopCarAdapter(Context mcontext, List<RecyBean.DataBean> datas) {
        super(mcontext, datas);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shop_iteam_child;

    }

    @Override
    protected void convert(ViewHolder viewHolder, RecyBean.DataBean dataBean, int postion) {
        viewHolder.setText(R.id.shopcar_name, dataBean.getSellerName());
        RecyclerView recyShop = (RecyclerView) viewHolder.getView(R.id.shopcar_shop);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyShop.setLayoutManager(staggeredGridLayoutManager);
        ShoppingAdapter shoppingAdapter = new ShoppingAdapter(context, dataBean.getList());
        shoppingAdapter.click(click);
        recyShop.setAdapter(shoppingAdapter);

    }
}
