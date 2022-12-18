package com.eb.easy_bookkeeping.frag_record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eb.easy_bookkeeping.R;
import com.eb.easy_bookkeeping.db.DBManager;
import com.eb.easy_bookkeeping.db.TypeBean;

import java.util.Calendar;
import java.util.List;

/**
 * 支出模块
 */
public class OutcomeFragment extends BaseRecordFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_outcome, container, false);
        initView(view);
        setInitTime();
        //给GridView填充数据的方法
        loadDataToGV();
        setGVListener(); //设置GridView每一项的点击事件
        setBeizhuByTime();
        return view;
    }
    /*根据时间自动填写备注*/
    private void setBeizhuByTime() {
        String beizhu;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour < 11) {
            beizhu = "早饭";
        }else if (hour < 16) {
            beizhu = "午饭";
        }else {
            beizhu = "晚饭";
        }
        beizhuTv.setText(beizhu);
        accountBean.setBeizhu(beizhu);
    }
    // 重写
    @Override
    void loadDataToGV() {
        super.loadDataToGV();
        //获取数据库当中的数据源
        List<TypeBean> outlist = DBManager.getTypeList(0);
        typeList.addAll(outlist);
        adapter.notifyDataSetChanged();
        typeTv.setText(outlist.get(0).getTypename());
        typeIv.setImageResource(outlist.get(0).getImageId());
        accountBean.setTypename(outlist.get(0).getTypename());
        accountBean.setImageId(outlist.get(0).getImageId());
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(0);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
