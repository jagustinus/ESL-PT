package com.example.progtech.easierstudentlife.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserData implements Parcelable {
    public String ifullName, iEmail, iUniv, iMajor;
    private List<Todo> dataTodo;

    public UserData() {
    }

    public UserData(String ifullName, String iEmail, String iUniv, List<Todo> dataTodo) {
        this.ifullName = ifullName;
        this.iEmail = iEmail;
        this.iUniv = iUniv;
        this.dataTodo = dataTodo;
    }

    protected UserData(Parcel in) {
        ifullName = in.readString();
        iEmail = in.readString();
        iUniv = in.readString();
        iMajor = in.readString();
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ifullName);
        dest.writeString(iEmail);
        dest.writeString(iUniv);
        dest.writeString(iMajor);
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

    public String getiMajor() {
        return iMajor;
    }

    public void setiMajor(String iMajor) {
        this.iMajor = iMajor;
    }

    public List<Todo> getDataTodo() {
        return dataTodo;
    }

    public void setDataTodo(List<Todo> dataTodo) {
        this.dataTodo = dataTodo;
    }
}
