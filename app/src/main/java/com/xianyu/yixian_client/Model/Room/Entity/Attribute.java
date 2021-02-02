package com.xianyu.yixian_client.Model.Room.Entity;

import com.google.gson.annotations.Expose;

public class Attribute {
    public enum Category {Physics,Magic,Attack,Cure,Eternal };
    @Expose
    Category category;//属性

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public Attribute(Category category){
        this.category = category;
    }
}
