package com.eb.easy_bookkeeping.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.eb.easy_bookkeeping.R;

public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(@Nullable Context context) {
        super(context,"eb.db" , null, 1);
    }

    //    创建数据库的方法，只有项目第一次运行时，会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
//        创建表示类型的表
        String sql = "create table typetb(id integer primary key autoincrement,typename varchar(10),imageId integer,kind integer)";
        db.execSQL(sql);
        insertType(db);
        //创建记账表
        sql = "create table accounttb(id integer primary key autoincrement,typename varchar(10),ImageId integer,beizhu varchar(80),money float," +
                "time varchar(60),year integer,month integer,day integer,kind integer)";
        db.execSQL(sql);
        createFoodCostSumView(db);
    }

    private void createFoodCostSumView(SQLiteDatabase db) {
        String sql = "create view foodcostsumview as select sum(money) as foodcost from accounttb where kind = 0 and typename in (select typename from typetb where id = 1 )";
        db.execSQL(sql);
    }

    private void insertType(SQLiteDatabase db) {
//      向typetb表当中插入元素
        String sql = "insert into typetb (typename,imageId,kind) values (?,?,?)";
        db.execSQL(sql,new Object[]{"三餐", R.mipmap.ic_meal,0});
        db.execSQL(sql,new Object[]{"零食", R.mipmap.ic_snack,0});
        db.execSQL(sql,new Object[]{"水果", R.mipmap.ic_fruit,0});
        db.execSQL(sql,new Object[]{"饮品", R.mipmap.ic_drink,0});
        db.execSQL(sql,new Object[]{"日用", R.mipmap.ic_daily,0});
        db.execSQL(sql,new Object[]{"通讯", R.mipmap.ic_comm,0});
        db.execSQL(sql,new Object[]{"交通", R.mipmap.ic_traffic,0});
        db.execSQL(sql,new Object[]{"服饰", R.mipmap.ic_clothes,0});
        db.execSQL(sql,new Object[]{"学习", R.mipmap.ic_study,0});
        db.execSQL(sql,new Object[]{"娱乐", R.mipmap.ic_game,0});
        db.execSQL(sql,new Object[]{"数码", R.mipmap.ic_digital,0});
        db.execSQL(sql,new Object[]{"礼品", R.mipmap.ic_gift,0});
        db.execSQL(sql,new Object[]{"购物", R.mipmap.ic_shopping,0});
        db.execSQL(sql,new Object[]{"医疗", R.mipmap.ic_medical,0});
        db.execSQL(sql,new Object[]{"其他", R.mipmap.ic_other,0});

        db.execSQL(sql,new Object[]{"工资", R.mipmap.ic_salary,1});
        db.execSQL(sql,new Object[]{"红包", R.mipmap.ic_hongbao,1});
        db.execSQL(sql,new Object[]{"打赏", R.mipmap.ic_reward,1});
        db.execSQL(sql,new Object[]{"利息", R.mipmap.ic_interest,1});
        db.execSQL(sql,new Object[]{"兼职", R.mipmap.ic_parttime,1});
        db.execSQL(sql,new Object[]{"其他", R.mipmap.ic_other,1});

    }

    // 数据库版本在更新时发生改变，会调用此方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
