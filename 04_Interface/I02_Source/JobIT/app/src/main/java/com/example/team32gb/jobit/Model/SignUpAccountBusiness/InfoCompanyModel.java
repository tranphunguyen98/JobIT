package com.example.team32gb.jobit.Model.SignUpAccountBusiness;

import android.os.Parcel;
import android.os.Parcelable;

public class InfoCompanyModel implements Parcelable {
    String name;
    String type;
    String size;

    String address;
    String province;
    String introduce;
    String namePresenter;
    String phoneNumberPresenter;

    public InfoCompanyModel() {
    }

    protected InfoCompanyModel(Parcel in) {
        name = in.readString();
        type = in.readString();
        size = in.readString();
        address = in.readString();
        province = in.readString();
        introduce = in.readString();
        namePresenter = in.readString();
        phoneNumberPresenter = in.readString();
    }

    public static final Creator<InfoCompanyModel> CREATOR = new Creator<InfoCompanyModel>() {
        @Override
        public InfoCompanyModel createFromParcel(Parcel in) {
            return new InfoCompanyModel(in);
        }

        @Override
        public InfoCompanyModel[] newArray(int size) {
            return new InfoCompanyModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getNamePresenter() {
        return namePresenter;
    }

    public void setNamePresenter(String namePresenter) {
        this.namePresenter = namePresenter;
    }

    public String getPhoneNumberPresenter() {
        return phoneNumberPresenter;
    }

    public void setPhoneNumberPresenter(String phoneNumberPresenter) {
        this.phoneNumberPresenter = phoneNumberPresenter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(size);
        dest.writeString(address);
        dest.writeString(province);
        dest.writeString(introduce);
        dest.writeString(namePresenter);
        dest.writeString(phoneNumberPresenter);
    }
}
