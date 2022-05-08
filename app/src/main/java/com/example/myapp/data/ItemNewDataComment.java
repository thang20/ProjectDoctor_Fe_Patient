package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewDataComment implements Parcelable  {
    private String imageFace, time, date, fullname, image, content;


    protected ItemNewDataComment(Parcel in) {
        imageFace = in.readString();
        time = in.readString();
        date = in.readString();
        fullname = in.readString();
        image= in.readString();
        content = in.readString();


    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageFace);
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(fullname);
        dest.writeString(image);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewDataComment> CREATOR = new Creator<ItemNewDataComment>() {
        @Override
        public ItemNewDataComment createFromParcel(Parcel in) {
            return new ItemNewDataComment(in);
        }

        @Override
        public ItemNewDataComment[] newArray(int size) {
            return new ItemNewDataComment[size];
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public static Creator<ItemNewDataComment> getCREATOR() {
        return CREATOR;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public ItemNewDataComment(String imageFace, String time, String date, String fullname, String image, String content) {
        this.imageFace = imageFace;
        this.time = time;
        this.date = date;
        this.fullname = fullname;
        this.image = image;

        this.content = content;

    }


}
