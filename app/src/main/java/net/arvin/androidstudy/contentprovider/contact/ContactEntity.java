package net.arvin.androidstudy.contentprovider.contact;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arvinljw on 2018/7/9 18:53
 * Function：
 * Desc：
 */
public class ContactEntity implements Parcelable {
    private String pinyinName;
    private String name;
    private String phoneNum;

    public String getPinyinName() {
        return pinyinName;
    }

    public void setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public ContactEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pinyinName);
        dest.writeString(this.name);
        dest.writeString(this.phoneNum);
    }

    protected ContactEntity(Parcel in) {
        this.pinyinName = in.readString();
        this.name = in.readString();
        this.phoneNum = in.readString();
    }

    public static final Creator<ContactEntity> CREATOR = new Creator<ContactEntity>() {
        @Override
        public ContactEntity createFromParcel(Parcel source) {
            return new ContactEntity(source);
        }

        @Override
        public ContactEntity[] newArray(int size) {
            return new ContactEntity[size];
        }
    };
}
