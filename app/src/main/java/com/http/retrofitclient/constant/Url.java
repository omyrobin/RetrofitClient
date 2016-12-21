package com.http.retrofitclient.constant;



public class Url {
	
	public static final String THE_TESTING_SERVER = "http://testapi.m.hotread.com/story-app/";
	
	public static String NOVEL_SERVER_ADDRESS = THE_TESTING_SERVER;

	public static final String LOGIN  = "m/clientaction/story/login";//登陆

	public static final String MYSHELF  = "m/clientview/view_myshelf";//我的书架
	
	public static final String SEARCH = "api/v2/story/common/search/10/%s?%s&%sdesc=1";//搜索
	
	public static final String SEARCH_KEYWORD = "/api/v2/story/common/search?q=%s";//搜索关键字

	public static final String READING ="m/clientview/view_chapter_content?id=%s";//根据章节ID阅读

}
