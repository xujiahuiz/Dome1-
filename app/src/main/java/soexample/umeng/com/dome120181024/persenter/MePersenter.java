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

public class MePersenter extends AppIDetegate {
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
                    /**
                     **
                     * 点击全选
                     * */
                    case R.id.shop_ck:
                        if (flag) {
                            //为true的时候设置一张选中的图片
                            shopck.setImageResource(R.drawable.cricle_yes);
//                            for循环进行获取之后累加数量以及价钱

                            for (int i = 0; i < recyBean.getData().size(); i++) {
                                for (int j = 0; j < recyBean.getData().get(i).getList().size(); j++) {
                                    recyBean.getData().get(i).getList().get(j).setIschecked(true);
                                    price += recyBean.getData().get(i).getList().get(j).getPrice();
                                    num++;
                                }
                            }
                            //更新商家adapter
                            shopCarAdapter.notifyDataSetChanged();
                        } else {
                            //为false的时候设置一张选中的图片
                            shopck.setImageResource(R.drawable.cricle_no);
                            for (int i = 0; i < recyBean.getData().size(); i++) {
                                for (int j = 0; j < recyBean.getData().get(i).getList().size(); j++) {
                                    recyBean.getData().get(i).getList().get(j).setIschecked(false);


                                }
                            }
//                            else时要在for循环外给价钱以及数量赋值为0
                            price = 0;
                            num = 0;
                            //更新商家adapter
                            shopCarAdapter.notifyDataSetChanged();
                        }
                        flag = !flag;
                        getPrice();
                        break;
                }
            }


        }, R.id.shop_ck);
    }

    /**
     * 设置底部的价格及数量
     */
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
                //设置单个选中还有加减号的数量价钱（通过回调）
                shopCarAdapter.setClick(new ShoppingAdapter.OkClick() {
                    @Override
                    public void click() {

                        for (int i = 0; i < recyBean.getData().size(); i++) {
                            for (int j = 0; j < recyBean.getData().get(i).getList().size(); j++) {
                                if (recyBean.getData().get(i).getList().get(j).isIschecked()) {
                                    RecyBean.DataBean.ListBean listBean = recyBean.getData().get(i).getList().get(j);

                                    price += listBean.getPrice() * listBean.getSelectnum();
                                    num += listBean.getSelectnum();
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