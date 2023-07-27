package com.example.test_task_android;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String name;
    private int age;
    private boolean isStudent;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.isStudent = false;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }
    protected User(Parcel in) {
        name = in.readString();
        age = in.readInt();
        isStudent = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeByte((byte) (isStudent ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }
}