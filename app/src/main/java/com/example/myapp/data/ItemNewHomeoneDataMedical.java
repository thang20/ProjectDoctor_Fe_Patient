package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewHomeoneDataMedical implements Parcelable  {
    private String ex, kq, drug, hospital, spec, time, phonedt, namedt;



    protected ItemNewHomeoneDataMedical(Parcel in) {
        ex = in.readString();
        kq = in.readString();
        drug = in.readString();


        hospital = in.readString();
        spec = in.readString();
        time = in.readString();

        phonedt = in.readString();
        namedt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ex);
        dest.writeString(kq);
        dest.writeString(drug);


        dest.writeString(hospital);
        dest.writeString(spec);
        dest.writeString(time);

        dest.writeString(namedt);
        dest.writeString(phonedt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewHomeoneDataMedical> CREATOR = new Creator<ItemNewHomeoneDataMedical>() {
        @Override
        public ItemNewHomeoneDataMedical createFromParcel(Parcel in) {
            return new ItemNewHomeoneDataMedical(in);
        }

        @Override
        public ItemNewHomeoneDataMedical[] newArray(int size) {
            return new ItemNewHomeoneDataMedical[size];
        }
    };

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getKq() {
        return kq;
    }

    public void setKq(String kq) {
        this.kq = kq;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }



    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhonedt() {
        return phonedt;
    }

    public void setPhonedt(String phonedt) {
        this.phonedt = phonedt;
    }

    public String getNamedt() {
        return namedt;
    }

    public void setNamedt(String namedt) {
        this.namedt = namedt;
    }

    public static Creator<ItemNewHomeoneDataMedical> getCREATOR() {
        return CREATOR;
    }

    public ItemNewHomeoneDataMedical(String ex, String kq  , String drug, String hospital,
                                     String spec, String time, String namedt, String phonedt) {
        this.ex = ex;
        this.kq = kq;
        this.drug = drug;

        this.hospital = hospital;
        this.spec = spec;
        this.time = time;

        this.namedt = namedt;
        this.phonedt = phonedt;


    }


}
