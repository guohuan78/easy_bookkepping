package com.eb.easy_bookkeeping.frag_record;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.eb.easy_bookkeeping.R;
import com.eb.easy_bookkeeping.db.TypeBean;
import java.util.List;

class TypeBaseAdapter extends BaseAdapter {
    private final Context context;
    private final List<TypeBean>mDatas;
    int selectPos = 0;  //选中位置
    public TypeBaseAdapter(Context context, List<TypeBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    // 此适配器不考虑复用问题，因为所有的item都显示在界面上，不会因为滑动就消失，所有没有剩余的convertView，所以不用复写
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_recordfrag_gv,parent,false);
        //查找布局当中的控件
        ImageView iv = convertView.findViewById(R.id.item_record_frag_iv);
        TextView tv = convertView.findViewById(R.id.item_record_frag_tv);
        //获取指定位置的数据源
        TypeBean typeBean = mDatas.get(position);
        tv.setText(typeBean.getTypename());
        iv.setImageResource(typeBean.getImageId());
        return convertView;
    }
}
