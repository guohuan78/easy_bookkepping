package com.eb.easy_bookkeeping.db;
//表示收入或支出具体类型的类
public class TypeBean {
    private int id;
    private String typename;   //类型名称
    private int ImageId;    //图片id
    private int kind;     //收入-1  支出-0

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int ImageId) {
        this.ImageId = ImageId;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public TypeBean() {
    }

    public TypeBean(int id, String typename, int ImageId, int kind) {
        this.id = id;
        this.typename = typename;
        this.ImageId = ImageId;
        this.kind = kind;
    }
}
