package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewDataService implements Parcelable  {
    private String content, money, image, hName;



    protected ItemNewDataService(Parcel in) {
        content = in.readString();
        money = in.readString();
        image = in.readString();
        hName = in.readString();



    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(image);
        dest.writeString(money);
        dest.writeString(hName);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewDataService> CREATOR = new Creator<ItemNewDataService>() {
        @Override
        public ItemNewDataService createFromParcel(Parcel in) {
            return new ItemNewDataService(in);
        }

        @Override
        public ItemNewDataService[] newArray(int size) {
            return new ItemNewDataService[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public static Creator<ItemNewDataService> getCREATOR() {
        return CREATOR;
    }

    public ItemNewDataService(String content, String money, String image, String hName) {
        this.content = content;
        this.money = money;
        this.image = image;
        this.hName = hName;


    }


}
