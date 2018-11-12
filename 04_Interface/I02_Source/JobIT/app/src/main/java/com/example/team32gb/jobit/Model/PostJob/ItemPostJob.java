package com.example.team32gb.jobit.Model.PostJob;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemPostJob implements Parcelable{
    String idJob;
    String idCompany;
    String nameCompany;
    DataPostJob dataPostJob;

    protected ItemPostJob(Parcel in) {
        idJob = in.readString();
        idCompany = in.readString();
        nameCompany = in.readString();
        dataPostJob = in.readParcelable(DataPostJob.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idJob);
        dest.writeString(idCompany);
        dest.writeString(nameCompany);
        dest.writeParcelable(dataPostJob, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemPostJob> CREATOR = new Creator<ItemPostJob>() {
        @Override
        public ItemPostJob createFromParcel(Parcel in) {
            return new ItemPostJob(in);
        }

        @Override
        public ItemPostJob[] newArray(int size) {
            return new ItemPostJob[size];
        }
    };

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public DataPostJob getDataPostJob() {
        return dataPostJob;
    }

    public void setDataPostJob(DataPostJob dataPostJob) {
        this.dataPostJob = dataPostJob;
    }

    public ItemPostJob() {

    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }
}
