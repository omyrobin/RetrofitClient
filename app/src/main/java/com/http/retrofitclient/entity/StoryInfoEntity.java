package com.http.retrofitclient.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 与StoryInfoEntity相同
 * @author Administrator
 *
 */
public class StoryInfoEntity implements Serializable{
    public long id;
    public long userId;
    public String name;
    public String author;
    public String cover;
    public String introduce;
	public String recommendedIntroduce;
    public String type;
    public int wordNumber;
    public String state;
    public int isPay;
    public String latestChapter;
    public int isOnShelf;
    public int chapterNumber;
    public String createDate;
    public String latestRevisionDate;
	
	public String idStr;
	
	public String userIdStr;
	
    public ArrayList<String> tag;
    public int monthTicket;
    public int recommendTicket;//总推荐
    public int clickRate;
	public int monthRecommendTicket;//月推荐
	public int monthTicketRanking; //月票排名
	public int chaMonthTicket; //超越前一名还需的月票数量
    public int monthMonthTicket;//当月月票
	public String channel;//当前书籍的对应频道
	public int rewardTotal;//打赏
	public int onShelfTotal;//收藏数
	
	@Override
	public boolean equals(Object o) {
		if(o!=null){
			return o.hashCode() == this.hashCode();
		}
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		
		return (id+"").hashCode();
	}
	
}
