package com.example.aidlserverdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class People implements Parcelable {
    private String name;
    private String sex;
    private int age;


    public People(){

    }

    public People(String name, String sex, int age){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }


    protected People(Parcel in) {
        name = in.readString();
        sex = in.readString();
        age = in.readInt();
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeInt(age);
    }
}
