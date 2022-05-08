package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewHomeoneDataSpec implements Parcelable  {
    private String name1,   name2;



    protected ItemNewHomeoneDataSpec(Parcel in) {
        name1 = in.readString();

        name2 = in.readString();




    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name1);

        dest.writeString(name2);



    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewHomeoneDataSpec> CREATOR = new Creator<ItemNewHomeoneDataSpec>() {
        @Override
        public ItemNewHomeoneDataSpec createFromParcel(Parcel in) {
            return new ItemNewHomeoneDataSpec(in);
        }

        @Override
        public ItemNewHomeoneDataSpec[] newArray(int size) {
            return new ItemNewHomeoneDataSpec[size];
        }
    };

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }



    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }



    public static Creator<ItemNewHomeoneDataSpec> getCREATOR() {
        return CREATOR;
    }

    public ItemNewHomeoneDataSpec(String name1, String name2) {
        this.name1 = name1;


        this.name2 = name2;



    }


}
