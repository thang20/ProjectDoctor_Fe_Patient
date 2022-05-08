package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewDataQADoctor implements Parcelable  {
    private String imageFace, time, date, fullname, content, idpeople, idpost;


    protected ItemNewDataQADoctor(Parcel in) {
        imageFace = in.readString();
        time = in.readString();
        date = in.readString();
        fullname = in.readString();
        content = in.readString();
        idpeople = in.readString();
        idpost = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageFace);
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(fullname);
        dest.writeString(content);
        dest.writeString(idpeople);
        dest.writeString(idpost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewDataQADoctor> CREATOR = new Creator<ItemNewDataQADoctor>() {
        @Override
        public ItemNewDataQADoctor createFromParcel(Parcel in) {
            return new ItemNewDataQADoctor(in);
        }

        @Override
        public ItemNewDataQADoctor[] newArray(int size) {
            return new ItemNewDataQADoctor[size];
        }
    };

    public String getImageFace() {
        return imageFace;
    }

    public void setImageFace(String imageFace) {
        this.imageFace = imageFace;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public static Creator<ItemNewDataQADoctor> getCREATOR() {
        return CREATOR;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIdpeople() {
        return idpeople;
    }

    public void setIdpeople(String idpeople) {
        this.idpeople = idpeople;
    }

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public ItemNewDataQADoctor(String imageFace, String time, String date, String fullname, String content, String idpeople, String idpost) {
        this.imageFace = imageFace;
        this.time = time;
        this.date = date;
        this.fullname = fullname;
        this.content = content;
        this.idpeople = idpeople;
        this.idpost = idpost;
    }


}
