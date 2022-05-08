package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemProfileDataRemind implements Parcelable  {
    private String remind, time, date;


    protected ItemProfileDataRemind(Parcel in) {
        remind = in.readString();
        time = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(remind);
        dest.writeString(time);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemProfileDataRemind> CREATOR = new Creator<ItemProfileDataRemind>() {
        @Override
        public ItemProfileDataRemind createFromParcel(Parcel in) {
            return new ItemProfileDataRemind(in);
        }

        @Override
        public ItemProfileDataRemind[] newArray(int size) {
            return new ItemProfileDataRemind[size];
        }
    };

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

    public static Creator<ItemProfileDataRemind> getCREATOR() {
        return CREATOR;
    }

    public ItemProfileDataRemind(String remind, String time, String date) {
        this.remind = remind;
        this.time = time;
        this.date = date;
    }


}
