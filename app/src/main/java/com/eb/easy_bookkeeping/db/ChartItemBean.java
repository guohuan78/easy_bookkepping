package com.eb.easy_bookkeeping.db;

public class ChartItemBean {
    private int ImageId;
    private String type;
    private float ratio;   //所占比例
    private float totalMoney;  //此项的总钱数

    public ChartItemBean() {
    }

    public void setImageId(int ImageId) {
        this.ImageId = ImageId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getImageId() {
        return ImageId;
    }

    public String getType() {
        return type;
    }

    public float getRatio() {
        return ratio;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public ChartItemBean(int ImageId, String type, float ratio, float totalMoney) {
        this.ImageId = ImageId;
        this.type = type;
        this.ratio = ratio;
        this.totalMoney = totalMoney;
    }
}
