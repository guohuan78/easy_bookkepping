package com.eb.easy_bookkeeping.frag_record;

import com.eb.easy_bookkeeping.db.DBManager;
import com.eb.easy_bookkeeping.db.TypeBean;

import java.util.List;

/**
 * 收入记录页面
 */
public class IncomeFragment extends BaseRecordFragment {

    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        //获取数据库当中的数据源
        List<TypeBean> inlist = DBManager.getTypeList(1);
        typeList.addAll(inlist);
        adapter.notifyDataSetChanged();
        typeTv.setText(inlist.get(0).getTypename());
        typeIv.setImageResource(inlist.get(0).getImageId());
        accountBean.setTypename(inlist.get(0).getTypename());
        accountBean.setImageId(inlist.get(0).getImageId());
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(1);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
