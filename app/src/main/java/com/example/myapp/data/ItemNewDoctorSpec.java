package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewDoctorSpec implements Parcelable  {
    private String image, name, hospital, address, phone, chungchi;



    protected ItemNewDoctorSpec(Parcel in) {
        image = in.readString();
        name = in.readString();
        hospital = in.readString();
        address = in.readString();
        phone = in.readString();
        chungchi = in.readString();



    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(hospital);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(chungchi);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewDoctorSpec> CREATOR = new Creator<ItemNewDoctorSpec>() {
        @Override
        public ItemNewDoctorSpec createFromParcel(Parcel in) {
            return new ItemNewDoctorSpec(in);
        }

        @Override
        public ItemNewDoctorSpec[] newArray(int size) {
            return new ItemNewDoctorSpec[size];
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


    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChungchi() {
        return chungchi;
    }

    public void setChungchi(String chungchi) {
        this.chungchi = chungchi;
    }

    public static Creator<ItemNewDoctorSpec> getCREATOR() {
        return CREATOR;
    }

    public ItemNewDoctorSpec(String image, String name, String hospital, String address, String phone, String chungchi) {
        this.image = image;
        this.name = name;
        this.hospital = hospital;
        this.address = address;
        this.phone = phone;
        this.chungchi = chungchi;


    }


}
