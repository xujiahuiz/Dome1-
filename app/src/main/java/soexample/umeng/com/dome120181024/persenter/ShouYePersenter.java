package soexample.umeng.com.dome120181024.persenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import cn.bingoogolapple.bgabanner.BGABanner;
import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.bean.RecyBean;

import soexample.umeng.com.dome120181024.mvp.view.AppIDetegate;
import soexample.umeng.com.dome120181024.net.Http;
import soexample.umeng.com.dome120181024.net.HttpHelper;
import soexample.umeng.com.dome120181024.net.HttpRequestListener;

import soexample.umeng.com.dome120181024.net.RecycleAdapter;
import soexample.umeng.com.dome120181024.net.ViewHolder;

public class ShouYePersenter extends AppIDetegate {
    private Context mcontext;
    private BGABanner banner;
    private RecyclerView recycler;
    private TextView select;

    @Override
    protected int getLayout() {
        return R.layout.shouye_fragment_item;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        new HttpHelper(new HttpRequestListener() {
            @Override
            public void SuccessRequest(final String data) {
                if(data.contains("<")){
                    return;
                }
                Gson gson = new Gson();
                final RecyBean recyBean = gson.fromJson(data, RecyBean.class);
                //设定管理器
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

                recycler.setLayoutManager(staggeredGridLayoutManager);
                recycler.setAdapter(new RecycleAdapter<RecyBean.DataBean>(mcontext, recyBean.getData()) {

                    @Override
                    protected int getLayoutId() {
                        return R.layout.layout_iteam_child;
                    }

                    @Override
                    protected void convert(ViewHolder viewHolder, RecyBean.DataBean dataBean, int postion) {
                        viewHolder.setText(R.id.shop_name, dataBean.getSellerName());
                        RecyclerView recyShop = (RecyclerView) viewHolder.getView(R.id.recy_shop);
                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        recyShop.setLayoutManager(staggeredGridLayoutManager);
                        recyShop.setAdapter(new RecycleAdapter<RecyBean.DataBean.ListBean>(mcontext, recyBean.getData().get(postion).getList()) {

                            @Override
                            protected int getLayoutId() {
                                return R.layout.layout_child_item;
                            }

                            @Override
                            protected void convert(ViewHolder viewHolder, RecyBean.DataBean.ListBean listBean, int postion) {
                                String[] split = listBean.getImages().split("\\|");
                                viewHolder.setText(R.id.child_name,listBean.getTitle()).setImageUrl(R.id.child_pic,split[0]);
                            }
                        });
                    }
                });

            }

            @Override
            public void Filed(String msg) {

            }
        }).doGet(Http.SHOP_URL);
    }

    private void initView() {
        banner = (BGABanner) get(R.id.shouye_banner);
        recycler = (RecyclerView) get(R.id.shouye_recycler);
        select = (TextView) get(R.id.shouye_select);

    }

    @Override
    public void initContext(Context context) {
        this.mcontext = context;
    }
}
