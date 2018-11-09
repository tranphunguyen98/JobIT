package com.example.team32gb.jobit.Model.SignUpAccountBusiness;

import static com.example.team32gb.jobit.Utility.Config.NOT_APPROVAL;

public class InfoCompanyModel {
    protected String name;
    protected String type;
    protected String size;

    protected String address;
    protected String province;
    protected String introduce;
    protected String namePresenter;
    protected String phoneNumberPresenter;
    protected long approvalMode;

    public InfoCompanyModel() {
        approvalMode =NOT_APPROVAL;   //0: Chưa duyệt
    }

    public InfoCompanyModel(String name, String type, String size, String address, String province, String introduce, String namePresenter, String phoneNumberPresenter) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.address = address;
        this.province = province;
        this.introduce = introduce;
        this.namePresenter = namePresenter;
        this.phoneNumberPresenter = phoneNumberPresenter;
        approvalMode =NOT_APPROVAL;   //0: Chưa duyệt
    }

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
}
