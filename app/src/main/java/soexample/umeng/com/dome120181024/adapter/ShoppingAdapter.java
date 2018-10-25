package soexample.umeng.com.dome120181024.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.bean.RecyBean;
import soexample.umeng.com.dome120181024.net.RecycleAdapter;
import soexample.umeng.com.dome120181024.net.ViewHolder;

public class ShoppingAdapter extends RecycleAdapter<RecyBean.DataBean.ListBean> {
    private Context context;
    private EditText num;

    public ShoppingAdapter(Context mcontext, List<RecyBean.DataBean.ListBean> datas) {
        super(mcontext, datas);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shop_child_item;
    }

    @Override
    protected void convert(final ViewHolder viewHolder, final RecyBean.DataBean.ListBean listBean, final int postion) {
        String[] split = listBean.getImages().split("\\|");
        viewHolder.setText(R.id.c_name, listBean.getTitle()).setText(R.id.c_price, listBean.getPrice() + "").setImageUrl(R.id.c_img, split[0]).setText(R.id.txt_jia, "+").setText(R.id.txt_jian, "-");
        if (listBean.isIschecked()) {
            viewHolder.setImageResource(R.id.c_ck, R.drawable.cricle_yes);
        } else {
            viewHolder.setImageResource(R.id.c_ck, R.drawable.cricle_no);
        }
        //设置选择状态
        viewHolder.getView(R.id.c_ck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBean.isIschecked()) {
                    listBean.setIschecked(false);
                    viewHolder.setImageResource(R.id.c_ck, R.drawable.cricle_no);
                    okClick.click();
                    notifyItemChanged(postion);
                } else {
                    listBean.setIschecked(true);
                    viewHolder.setImageResource(R.id.c_ck, R.drawable.cricle_yes);
                    okClick.click();
                    notifyItemChanged(postion);
                }
            }
        });
        //设置选择数量
        num = (EditText) viewHolder.getView(R.id.txt_num);
        num.setText(listBean.getSelectnum() + "");

        viewHolder.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.txt_jia:
                        int selectnum = listBean.getSelectnum();
                        selectnum += 1;
                        setText(selectnum, listBean, postion);
                        break;
                    case R.id.txt_jian:
                        int selectnum1 = listBean.getSelectnum();
                        selectnum1 -= 1;
                        setText(selectnum1, listBean, postion);
                        break;
                }
            }
        }, R.id.txt_jia, R.id.txt_jian);
    }

    public void setText(int selectnum, RecyBean.DataBean.ListBean listBean, int postion) {
        if (selectnum < 0) {
            toase("不能為0");
            return;
        }
        listBean.setSelectnum(selectnum);
        notifyItemChanged(postion);
        okClick.click();
    }

    //单个点击回调方法
    public interface OkClick {
        void click();
    }

    private OkClick okClick;

    public void click(OkClick okClick) {
        this.okClick = okClick;
    }

    public void toase(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}





