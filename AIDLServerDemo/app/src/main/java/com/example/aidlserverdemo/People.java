package com.example.aidlserverdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class People implements Parcelable {
    //全部写成public只是减少set和get方法，实际操作请勿如此
    public String name;
    public String sex;
    public int age;

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

        dest.writeInt(age);
    }
}
