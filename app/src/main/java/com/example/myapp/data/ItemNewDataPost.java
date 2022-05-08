package com.example.myapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemNewDataPost implements Parcelable  {
    private String imageFace, time, date, fullname, image, numberlike, content, idpeople, idpost, like;


    protected ItemNewDataPost(Parcel in) {
        imageFace = in.readString();
        time = in.readString();
        date = in.readString();
        fullname = in.readString();
        image= in.readString();
        numberlike = in.readString();
        content = in.readString();
        idpeople = in.readString();
        idpost = in.readString();
        like = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageFace);
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(fullname);
        dest.writeString(image);
        dest.writeString(numberlike);
        dest.writeString(content);
        dest.writeString(idpeople);
        dest.writeString(idpost);
        dest.writeString(like);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemNewDataPost> CREATOR = new Creator<ItemNewDataPost>() {
        @Override
        public ItemNewDataPost createFromParcel(Parcel in) {
            return new ItemNewDataPost(in);
        }

        @Override
        public ItemNewDataPost[] newArray(int size) {
            return new ItemNewDataPost[size];
        }
    };

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

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

    public String getNumberlike() {
        return numberlike;
    }

    public void setNumberlike(String numberlike) {
        this.numberlike = numberlike;
    }

    public static Creator<ItemNewDataPost> getCREATOR() {
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

    public ItemNewDataPost(String imageFace, String time, String date, String fullname, String image, String numberlike, String content, String idpeople, String idpost, String like) {
        this.imageFace = imageFace;
        this.time = time;
        this.date = date;
        this.fullname = fullname;
        this.image = image;
        this.numberlike = numberlike;
        this.content = content;

        this.idpeople = idpeople;
        this.idpost = idpost;
        this.like = like;
    }


}
