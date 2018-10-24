package soexample.umeng.com.dome120181024.persenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.adapter.ShopCarAdapter;
import soexample.umeng.com.dome120181024.adapter.ShoppingAdapter;
import soexample.umeng.com.dome120181024.bean.RecyBean;
import soexample.umeng.com.dome120181024.mvp.view.AppIDetegate;
import soexample.umeng.com.dome120181024.net.Http;
import soexample.umeng.com.dome120181024.net.HttpHelper;
import soexample.umeng.com.dome120181024.net.HttpRequestListener;

public class MePersenter extends AppIDetegate{
    private RecyclerView shoplist;
    private ImageView shopck;
    private TextView allprice;
    private TextView connet;
    private Context mcontext;
    private boolean flag = true;
    private int price = 0;
    private int num = 0;
    private RecyBean recyBean;
    private ShopCarAdapter shopCarAdapter;

    @Override
    protected int getLayout() {
        return R.layout.me_fragment_item;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        getShopList();
        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.shop_ck:
                        if (flag) {
                            shopck.setImageResource(R.drawable.cricle_yes);
                            for (int i = 0; i < recyBean.getData().size(); i++) {
                                for (int j = 0; j < recyBean.getData().get(i).getList().size(); j++) {
                                    recyBean.getData().get(i).getList().get(j).setIschecked(true);
                                    price += recyBean.getData().get(i).getList().get(j).getPrice();
                                    num++;
                                }
                            }
                            shopCarAdapter.notifyDataSetChanged();
                        } else {
                            shopck.setImageResource(R.drawable.cricle_no);
                            for (int i = 0; i < recyBean.getData().size(); i++) {
                                for (int j = 0; j < recyBean.getData().get(i).getList().size(); j++) {
                                    recyBean.getData().get(i).getList().get(j).setIschecked(false);


                                }
                            }
                            price = 0;
                            num = 0;
                            shopCarAdapter.notifyDataSetChanged();
                        }
                        flag = !flag;
                        getPrice();
                        break;
                }
            }


        }, R.id.shop_ck);
    }
        public void getPrice() {
            allprice.setText("价格:" + price);
            connet.setText("数量:" + "(" + num + ")");
            price = 0;
            num = 0;
        }
    //获取购物车列表
    private void getShopList() {
        new HttpHelper(new HttpRequestListener() {
            @Override
            public void SuccessRequest(final String data) {

                if (data.contains("<")) {
                    return;
                }
                Gson gson = new Gson();
                recyBean = gson.fromJson(data, RecyBean.class);
                //设定管理器
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                shoplist.setLayoutManager(staggeredGridLayoutManager);
                shopCarAdapter = new ShopCarAdapter(mcontext, recyBean.getData());
                shopCarAdapter.setClick(new ShoppingAdapter.OkClick() {
                    @Override
                    public void click(boolean flag) {

                        for (int i = 0; i < recyBean.getData().size(); i++) {
                            for (int j = 0; j < recyBean.getData().get(i).getList().size(); j++) {
                                if (recyBean.getData().get(i).getList().get(j).isIschecked()){
                                price += recyBean.getData().get(i).getList().get(j).getPrice();
                                num++;
                                }
                            }
                        }
                        getPrice();
                    }
                });
                shoplist.setAdapter(shopCarAdapter);

            }

            @Override
            public void Filed(String msg) {

            }
        }).doGet(Http.SHOP_URL);
    }

    //初始化控件
    private void initView() {
        shoplist = (RecyclerView) get(R.id.shop_list);
        shopck = (ImageView) get(R.id.shop_ck);
        allprice = (TextView) get(R.id.shop_allprice);
        connet = (TextView) get(R.id.shop_connet);

    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mcontext = context;
    }
}