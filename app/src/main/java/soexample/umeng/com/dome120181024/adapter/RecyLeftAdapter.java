package soexample.umeng.com.dome120181024.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import soexample.umeng.com.dome120181024.R;
import soexample.umeng.com.dome120181024.bean.JiuBean;
import soexample.umeng.com.dome120181024.net.RecycleAdapter;
import soexample.umeng.com.dome120181024.net.ViewHolder;

public class RecyLeftAdapter extends RecycleAdapter<JiuBean.DataBean> {
    private final Context context;

    public RecyLeftAdapter(Context mcontext, List<JiuBean.DataBean> datas) {
        super(mcontext, datas);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.left_adapter;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final JiuBean.DataBean dataBean, int postion) {
        viewHolder.setImageUrl(R.id.left_pic, dataBean.getIcon()).setText(R.id.left_title, dataBean.getName());
        viewHolder.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cid = dataBean.getCid();
                    setClickCid.ClickOk(cid);
                }
        });
    }

    private SetClickCid setClickCid;

    public interface SetClickCid {
        void ClickOk(int Cid);
    }

    public void setSetClickCid(SetClickCid setClickCid) {
        this.setClickCid = setClickCid;
    }
}
