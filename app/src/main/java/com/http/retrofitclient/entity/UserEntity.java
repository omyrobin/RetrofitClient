package com.http.retrofitclient.entity;

import java.io.Serializable;


public class UserEntity implements Serializable{
	public long id;
	public String nickName;
	public String face_url;
	public String sex;
	public String sign;
	public String interest;
	public String introduction;
	public int isSign;
	public int fansNum;
	public int plusNum;
	public int big;
	public int level;
	public int nextBig;
	public int recommendTicket;
	public int isInterest;
	public int type;
	public long sort;
	public int isBinding;
	public String identification;
	public String isOperation;
	public String goldAmount = "0";
	public String giveAmount = "0";

	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				", nickName='" + nickName + '\'' +
				", face_url='" + face_url + '\'' +
				", sex='" + sex + '\'' +
				", sign='" + sign + '\'' +
				", interest='" + interest + '\'' +
				", introduction='" + introduction + '\'' +
				", isSign=" + isSign +
				", fansNum=" + fansNum +
				", plusNum=" + plusNum +
				", big=" + big +
				", level=" + level +
				", nextBig=" + nextBig +
				", recommendTicket=" + recommendTicket +
				", isInterest=" + isInterest +
				", type=" + type +
				", sort=" + sort +
				", isBinding=" + isBinding +
				", identification='" + identification + '\'' +
				", isOperation='" + isOperation + '\'' +
				", goldAmount='" + goldAmount + '\'' +
				", giveAmount='" + giveAmount + '\'' +
				'}';
	}
}
