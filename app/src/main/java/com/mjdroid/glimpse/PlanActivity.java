package com.mjdroid.glimpse;


import java.io.Serializable;

public class PlanActivity implements Serializable {
    private String mName;
    private String mLongText;
    private int mImageResource;
    private String mFromTime;
    private String mToTime;
    private String mDate;
    private boolean mHasCompany;
    private String mContact;


    public PlanActivity(String longText, int imageResource, String fromTime, String toTime, String date, boolean hasCompany, String contact) {
        mLongText = longText;
        mImageResource = imageResource;
        mFromTime = fromTime;
        mToTime = toTime;
        mDate = date;
        mHasCompany = hasCompany;
        mContact = contact;
    }

    public PlanActivity(String longText, int imageResource, String date, boolean hasCompany, String contact) {
        mLongText = longText;
        mImageResource = imageResource;
        mDate = date;
        mHasCompany = hasCompany;
        mContact = contact;
    }



    public String getLongText() {
        return mLongText;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getFromTime() {
        return mFromTime;
    }

    public String getToTime() {
        return mToTime;
    }

    public String getDate() {
        return mDate;
    }

    public String getContact() {
        return mContact;
    }

    public boolean getHasCompany() {
        return mHasCompany;
    }

    public void setLongText(String longText) {
        mLongText = longText;
    }

    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
    }

    public void setToTime(String toTime) {
        mToTime = toTime;
    }

    public void setFromTime(String fromTime) {
        mFromTime = fromTime;
    }

    public void setHasCompany(boolean hasCompany) {
        mHasCompany = hasCompany;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setContact(String contact) {
        mContact = contact;
    }

}
