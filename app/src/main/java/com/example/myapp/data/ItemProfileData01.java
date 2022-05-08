package com.example.myapp.data;

import java.io.Serializable;

public class ItemProfileData01 implements Serializable {
    private int resourceId1;
    private String name;
    private int resourceId2;

    public ItemProfileData01(int resourceId1, String name, int resourceId2) {
        this.resourceId1 = resourceId1;
        this.name = name;
        this.resourceId2 = resourceId2;
    }

    public int getResourceId1() {
        return resourceId1;
    }

    public void setResourceId1(int resourceId1) {
        this.resourceId1 = resourceId1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId2() {
        return resourceId2;
    }

    public void setResourceId2(int resourceId2) {
        this.resourceId2 = resourceId2;
    }
}
