package soexample.umeng.com.dome120181024.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.bean.RecyBean;
import soexample.umeng.com.dome120181024.net.RecycleAdapter;
import soexample.umeng.com.dome120181024.net.ViewHolder;

public class ShoppingAdapter extends RecycleAdapter<RecyBean.DataBean.ListBean> {
    private Context context;

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
        viewHolder.setText(R.id.c_name, listBean.getTitle()).setText(R.id.c_price, listBean.getPrice() + "").setImageUrl(R.id.c_img, split[0]);
        if (listBean.isIschecked()) {
            viewHolder.setImageResource(R.id.c_ck, R.drawable.cricle_yes);
        } else {
            viewHolder.setImageResource(R.id.c_ck, R.drawable.cricle_no);
        }
        viewHolder.getView(R.id.c_ck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBean.isIschecked()) {
                    listBean.setIschecked(false);
                    viewHolder.setImageResource(R.id.c_ck, R.drawable.cricle_no);
                    okClick.click(listBean.isIschecked());
                  notifyItemChanged(postion);
                } else {
                    listBean.setIschecked(true);
                    viewHolder.setImageResource(R.id.c_ck, R.drawable.cricle_yes);
                    okClick.click(listBean.isIschecked());
                    notifyItemChanged(postion);
                }
            }
        });
    }

    public interface OkClick {
        void click(boolean flag);
    }

    private OkClick okClick;

    public void  click(OkClick okClick) {
        this.okClick = okClick;
    }
}




