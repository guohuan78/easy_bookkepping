package com.eb.easy_bookkeeping.frag_record;

import com.eb.easy_bookkeeping.R;
import com.eb.easy_bookkeeping.db.DBManager;
import com.eb.easy_bookkeeping.db.TypeBean;

import java.util.List;

/**
 * 支出模块
 */
public class OutcomeFragment extends BaseRecordFragment {

    // 重写
    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        //获取数据库当中的数据源
        List<TypeBean> outlist = DBManager.getTypeList(0);
        typeList.addAll(outlist);
        adapter.notifyDataSetChanged();
        typeTv.setText("三餐");
        typeIv.setImageResource(R.mipmap.ic_meal);
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(0);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
