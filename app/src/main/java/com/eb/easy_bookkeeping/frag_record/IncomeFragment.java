package com.eb.easy_bookkeeping.frag_record;

import com.eb.easy_bookkeeping.R;
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
        typeTv.setText("工资");
        typeIv.setImageResource(R.mipmap.ic_salary);
        accountBean.setTypename("工资");
        accountBean.setImageId(R.mipmap.ic_salary);
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(1);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
