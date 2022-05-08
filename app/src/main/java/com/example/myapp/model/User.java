package com.example.myapp.model;

public class User {
    private String fullName;
    private String email;
    private String passWord;
    private String phone;
    private String bytearrayimage;
    private String dateofbirth;
    private String sex;
    private String city;
    private String township;
    private String ward;
    private String apartmentnumber;
    private float money;
    private int accumulatedpoints;


    public User(String fullName, String email, String passWord, String phone,
                String bytearrayimage, String dateofbirth, String sex,
                String city, String township, String ward, String apartmentnumber, float money, int accumulatedpoints) {
        this.fullName = fullName;
        this.email = email;
        this.passWord = passWord;
        this.phone = phone;
        this.bytearrayimage = bytearrayimage;
        this.dateofbirth = dateofbirth;
        this.sex = sex;
        this.city = city;
        this.township = township;
        this.ward = ward;
        this.apartmentnumber = apartmentnumber;
        this.money = money;
        this.accumulatedpoints = accumulatedpoints;

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
