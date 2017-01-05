package com.http.retrofitclient.entity;

import android.os.Parcel;
import android.os.Parcelable;


public class UserEntity implements Parcelable {

    /**
     * userId : 500888659
     * nickName : 游客yOeYE
     * facePic :
     * sex :
     * isBinding : 0
     * autoPayChapter : 0
     * state : 0
     * businessInfo :
     * tinyFacePic : http://p2.duyao001.com/image/article/15bcc989e86a8b1deb2dcc7286b23e59_90x90.png
     */

    private String userId;
    private String nickName;
    private String facePic;
    private String sex;
    private int isBinding;
    private int autoPayChapter;
    private String state;
    private String businessInfo;
    private String tinyFacePic;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(int isBinding) {
        this.isBinding = isBinding;
    }

    public int getAutoPayChapter() {
        return autoPayChapter;
    }

    public void setAutoPayChapter(int autoPayChapter) {
        this.autoPayChapter = autoPayChapter;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }

    public String getTinyFacePic() {
        return tinyFacePic;
    }

    public void setTinyFacePic(String tinyFacePic) {
        this.tinyFacePic = tinyFacePic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.nickName);
        dest.writeString(this.facePic);
        dest.writeString(this.sex);
        dest.writeInt(this.isBinding);
        dest.writeInt(this.autoPayChapter);
        dest.writeString(this.state);
        dest.writeString(this.businessInfo);
        dest.writeString(this.tinyFacePic);
    }

    public UserEntity() {
    }

    protected UserEntity(Parcel in) {
        this.userId = in.readString();
        this.nickName = in.readString();
        this.facePic = in.readString();
        this.sex = in.readString();
        this.isBinding = in.readInt();
        this.autoPayChapter = in.readInt();
        this.state = in.readString();
        this.businessInfo = in.readString();
        this.tinyFacePic = in.readString();
    }

    public static final Parcelable.Creator<UserEntity> CREATOR = new Parcelable.Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}
