package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewHomeoneDataHospital implements Parcelable  {
    private String name, address, image;



    protected ItemNewHomeoneDataHospital(Parcel in) {
        name = in.readString();
        address = in.readString();
        image = in.readString();



    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(address);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewHomeoneDataHospital> CREATOR = new Creator<ItemNewHomeoneDataHospital>() {
        @Override
        public ItemNewHomeoneDataHospital createFromParcel(Parcel in) {
            return new ItemNewHomeoneDataHospital(in);
        }

        @Override
        public ItemNewHomeoneDataHospital[] newArray(int size) {
            return new ItemNewHomeoneDataHospital[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public static Creator<ItemNewHomeoneDataHospital> getCREATOR() {
        return CREATOR;
    }

    public ItemNewHomeoneDataHospital(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;


    }


}
