package com.example.myapp.data;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
public class ItemProfileDataRelative implements Parcelable  {
    private String name, dateOfBirth, phone="", address;


    protected ItemProfileDataRelative(Parcel in) {
        name = in.readString();
        dateOfBirth = in.readString();
        phone = in.readString();
        address = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(dateOfBirth);
        dest.writeString(phone);
        dest.writeString(address);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemProfileDataRelative> CREATOR = new Creator<ItemProfileDataRelative>() {
        @Override
        public ItemProfileDataRelative createFromParcel(Parcel in) {
            return new ItemProfileDataRelative(in);
        }

        @Override
        public ItemProfileDataRelative[] newArray(int size) {
            return new ItemProfileDataRelative[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ItemProfileDataRelative(String name, String dateOfBirth, String phone, String address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
    }


}
