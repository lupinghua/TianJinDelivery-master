package com.inspur.tianjindelivery.entiy;

/**
 * Created by lixu on 2018/3/13.
 */

public class MainPageEntity extends MyBaseEntity{
    private int imgId;
    private String text;

    public MainPageEntity() {
    }

    public MainPageEntity(int imgId, String text) {
        this.imgId = imgId;
        this.text = text;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
