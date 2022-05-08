package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewDataHospital implements Parcelable  {
    private String name, address;


    protected ItemNewDataHospital(Parcel in) {
        name = in.readString();
        address = in.readString();



    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewDataHospital> CREATOR = new Creator<ItemNewDataHospital>() {
        @Override
        public ItemNewDataHospital createFromParcel(Parcel in) {
            return new ItemNewDataHospital(in);
        }

        @Override
        public ItemNewDataHospital[] newArray(int size) {
            return new ItemNewDataHospital[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Creator<ItemNewDataHospital> getCREATOR() {
        return CREATOR;
    }

    public ItemNewDataHospital(String name, String address) {
        this.name = name;
        this.address = address;


    }


}
