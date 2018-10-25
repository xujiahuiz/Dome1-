package soexample.umeng.com.dome120181024.persenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.adapter.RecyLeftAdapter;
import soexample.umeng.com.dome120181024.adapter.ShopNameAdapter;
import soexample.umeng.com.dome120181024.bean.BannerBean;
import soexample.umeng.com.dome120181024.bean.JiuBean;
import soexample.umeng.com.dome120181024.bean.ShopBean;
import soexample.umeng.com.dome120181024.mvp.view.AppIDetegate;
import soexample.umeng.com.dome120181024.net.Http;
import soexample.umeng.com.dome120181024.net.HttpHelper;
import soexample.umeng.com.dome120181024.net.HttpRequestListener;

public class ListPersenter extends AppIDetegate {
    private Context mcontext;
    private RecyclerView recyleft;
    private RecyclerView recyright;
    private JiuBean jiuBean;
    private int Cid = 1;

    @Override
    protected int getLayout() {
        return R.layout.layout_list_fragment;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        Log.d("Tag", "a");
        new HttpHelper(new HttpRequestListener() {
            @Override
            public void SuccessRequest(String data) {

                Log.d("Tag", "b");
                Gson gson = new Gson();
                jiuBean = gson.fromJson(data, JiuBean.class);
                setleftAdapter();

            }

            @Override
            public void Filed(String msg) {
                Toast.makeText(mcontext, msg, Toast.LENGTH_SHORT).show();
            }
        }).doGet(Http.JIU_URL);
    }

    private void setleftAdapter() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyleft.setLayoutManager(linearLayoutManager);
        RecyLeftAdapter recyLeftAdapter = new RecyLeftAdapter(mcontext, jiuBean.getData());
        recyLeftAdapter.setSetClickCid(new RecyLeftAdapter.SetClickCid() {
            @Override
            public void ClickOk(int Cid) {
                new HttpHelper(new HttpRequestListener() {
                    @Override
                    public void SuccessRequest(String data) {
                        if (data.contains("<")) {
                            return;
                        }
                        Gson gson = new Gson();
                        ShopBean shopBean = gson.fromJson(data, ShopBean.class);
                        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(mcontext);
                        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyright.setLayoutManager(linearLayoutManager1);
                        ShopNameAdapter shopNameAdapter = new ShopNameAdapter(mcontext,shopBean.getData());
                        recyright.setAdapter(shopNameAdapter);
                    }

                    @Override
                    public void Filed(String msg) {

                    }
                }).doGet("http://www.zhaoapi.cn/product/getProductCatagory?cid=" + Cid);
            }
        });
        recyleft.setAdapter(recyLeftAdapter);
    }

    private void initView() {
        recyleft = (RecyclerView) get(R.id.list_recy_left);
        recyright = (RecyclerView) get(R.id.list_recy_right);
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mcontext = context;
    }

}
