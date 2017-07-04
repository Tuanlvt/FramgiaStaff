package com.framgia.framgiastaff.data.model;

import java.io.Serializable;

/**
 * Created by levutantuan on 7/2/17.
 */

public class Employee implements Serializable {

    private String mId;
    private String mEmail;
    private String mSex;
    private String mDateWorking;
    private String mType;
    private String mLocation;

    public Employee(String id, String email, String sex, String dateWorking, String type,
            String location) {
        mId = id;
        mEmail = email;
        mSex = sex;
        mDateWorking = dateWorking;
        mType = type;
        mLocation = location;
    }

    public Employee() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
    }

    public String getDateWorking() {
        return mDateWorking;
    }

    public void setDateWorking(String dateWorking) {
        mDateWorking = dateWorking;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }
}
