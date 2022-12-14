package com.eb.easy_bookkeeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eb.easy_bookkeeping.R;
import com.eb.easy_bookkeeping.db.ChartItemBean;
import com.eb.easy_bookkeeping.utils.FloatUtils;

import java.util.List;

/*
* 账单详情页面，list view的适配器
* */
public class ChartItemAdapter extends BaseAdapter {
    private final List<ChartItemBean> mDatas;
    private final LayoutInflater inflater;
    public ChartItemAdapter(Context context, List<ChartItemBean> mDatas) {
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_chartfrag_lv,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
//        获取显示内容
        ChartItemBean bean = mDatas.get(position);
        holder.iv.setImageResource(bean.getImageId());
        holder.typeTv.setText(bean.getType());
        float ratio = bean.getRatio();
        String pert = FloatUtils.ratioToPercent(ratio);
        holder.ratioTv.setText(pert);

        holder.totalTv.setText("￥ "+bean.getTotalMoney());
        return convertView;
    }

    class ViewHolder{
        final TextView typeTv;
        final TextView ratioTv;
        final TextView totalTv;
        final ImageView iv;
        ViewHolder(View view){
            typeTv = view.findViewById(R.id.item_chart_frag_tv_type);
            ratioTv = view.findViewById(R.id.item_chart_frag_tv_pert);
            totalTv = view.findViewById(R.id.item_chart_frag_tv_sum);
            iv = view.findViewById(R.id.item_chart_frag_iv);
        }
    }
}
