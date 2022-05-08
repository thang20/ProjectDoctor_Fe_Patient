package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewHomeoneDataRelative implements Parcelable  {
    private String name, gender, age , relative, phone, address;



    protected ItemNewHomeoneDataRelative(Parcel in) {
        name = in.readString();
        gender = in.readString();
        age = in.readString();
        relative = in.readString();
        phone = in.readString();
        address = in.readString();



    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(age);
        dest.writeString(relative);
        dest.writeString(phone);
        dest.writeString(address);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewHomeoneDataRelative> CREATOR = new Creator<ItemNewHomeoneDataRelative>() {
        @Override
        public ItemNewHomeoneDataRelative createFromParcel(Parcel in) {
            return new ItemNewHomeoneDataRelative(in);
        }

        @Override
        public ItemNewHomeoneDataRelative[] newArray(int size) {
            return new ItemNewHomeoneDataRelative[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
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

    public static Creator<ItemNewHomeoneDataRelative> getCREATOR() {
        return CREATOR;
    }

    public ItemNewHomeoneDataRelative(String name, String gender, String age, String relative, String phone, String address) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.relative = relative;
        this.phone = phone;
        this.address = address;


    }


}
