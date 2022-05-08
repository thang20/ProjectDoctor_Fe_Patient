package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemProfileDataHistory implements Parcelable  {
    private String remind, time, date, money, moneyadd, type;


    protected ItemProfileDataHistory(Parcel in) {
        remind = in.readString();
        time = in.readString();
        date = in.readString();
        money = in.readString();
        moneyadd = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(remind);
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(money);
        dest.writeString(moneyadd);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemProfileDataHistory> CREATOR = new Creator<ItemProfileDataHistory>() {
        @Override
        public ItemProfileDataHistory createFromParcel(Parcel in) {
            return new ItemProfileDataHistory(in);
        }

        @Override
        public ItemProfileDataHistory[] newArray(int size) {
            return new ItemProfileDataHistory[size];
        }
    };

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoneyadd() {
        return moneyadd;
    }

    public void setMoneyadd(String moneyadd) {
        this.moneyadd = moneyadd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static Creator<ItemProfileDataHistory> getCREATOR() {
        return CREATOR;
    }

    public ItemProfileDataHistory(String remind, String time, String date, String money, String moneyadd, String type) {
        this.remind = remind;
        this.time = time;
        this.date = date;
        this.money = money;
        this.moneyadd = moneyadd;
        this.type = type;
    }


}
