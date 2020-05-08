package com.example.progtech.easierstudentlife.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {
    public String ifullName, iEmail, iUniv;

    public UserData() {
    }

    public UserData(String ifullName, String iEmail, String iUniv) {
        this.ifullName = ifullName;
        this.iEmail = iEmail;
        this.iUniv = iUniv;
    }

    protected UserData(Parcel in) {
        ifullName = in.readString();
        iEmail = in.readString();
        iUniv = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ifullName);
        dest.writeString(iEmail);
        dest.writeString(iUniv);
    }

    public String getIfullName() {
        return ifullName;
    }

    public void setIfullName(String ifullName) {
        this.ifullName = ifullName;
    }

    public String getiEmail() {
        return iEmail;
    }

    public void setiEmail(String iEmail) {
        this.iEmail = iEmail;
    }

    public String getiUniv() {
        return iUniv;
    }

    public void setiUniv(String iUniv) {
        this.iUniv = iUniv;
    }
}
