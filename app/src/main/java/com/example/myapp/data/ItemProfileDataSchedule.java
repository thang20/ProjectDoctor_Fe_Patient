package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemProfileDataSchedule implements Parcelable  {
    private String remind, time, date;


    protected ItemProfileDataSchedule(Parcel in) {
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

    public static final Creator<ItemProfileDataSchedule> CREATOR = new Creator<ItemProfileDataSchedule>() {
        @Override
        public ItemProfileDataSchedule createFromParcel(Parcel in) {
            return new ItemProfileDataSchedule(in);
        }

        @Override
        public ItemProfileDataSchedule[] newArray(int size) {
            return new ItemProfileDataSchedule[size];
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

    public static Creator<ItemProfileDataSchedule> getCREATOR() {
        return CREATOR;
    }

    public ItemProfileDataSchedule(String remind, String time, String date) {
        this.remind = remind;
        this.time = time;
        this.date = date;
    }


}
