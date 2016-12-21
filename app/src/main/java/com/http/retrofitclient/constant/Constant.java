package com.http.retrofitclient.constant;

public class Constant {
	
	public static final int NOT_LOGGED  = 0;//未登录
	
	public static final int TOURIST = 1;//游客
	
	public static final int LOGIN = 2;//登陆
	
	public static final String APP_ID = "wx8373f34d99d83f4e";//微信appid
	
	public static final String SECRET = "d4624c36b6795d1d99dcf0547af5443d";//微信SECRET
	
	public static final String IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH";//首次安装app
	
	public static final String IS_FIRST_LAUNCH_TO_COMMENT = "IS_FIRST_LAUNCH_TO_COMMENT";//首次安装app 不评价
	
	public static final String LASTTIME = "LASTTIME";//上次广告页显示的时间戳
	
	public static final String COMMENT_APP = "COMMENT_APP";//跳转市场进行评论
	
	public static final String TOURIST_ID = "TOURIST_ID";//游客ID
	
	public static final String CHAPTER_ID = "CHAPTER_ID";
	
	public static final String CHAPTER_INDEX = "CHAPTER_INDEX";
	
	public static final String CHAPTER_PAGE = "CHAPTER_PAGE";
	
	public static final String CHAPTER_NAME = "CHAPTER_NAME";
	
	public static final String CHAPTER_SCROLL_Y = "CHAPTER_SCROLL_Y";
	
	public static final String READ_MODE = "READ_MODE";//阅读方式
	
	public static final String TXT_SIZE = "TXT_SIZE";//阅读字体大小
	
//	public static final String TXT_COLOR = "TXT_COLOR";//阅读字体颜色
	
	public static final String TXT_COLOR_INDEX = "TXT_COLOR_INDEX";//阅读字体颜色下标
	
	public static final String BG_INDEX = "BG_INDEX";//阅读背景颜色
	
	public static final String BG_SUN_INDEX = "BG_SUN_INDEX";//阅读日间背景颜色
	
	public static final String FLOWING_SYSYTEM = "FLOWING_SYSYTEM";//跟随系统亮度
	
	public static final int POPUPWINDOW_LEFT = 0;
	
	public static final int POPUPWINDOW_TOP_CHILD = 2;
	
	public static final int POPUPWINDOW_TOB_BOTTOM = 1;
	
	public static final String HEADER_X_I = "X-I";

	public static final String HEADER_X_S = "X-S";

	public static final String HEADER_X_SSID = "X-SSID";
	
	public static final String HEADER_C = "C";
	
	public static final String SET_COKKIE = "Set-Cookie";
	
	public static final String MALE_HOT_CHANNEL_JSON = "MALE_HOT_CHANNEL_JSON";
	
	public static final String FEMALE_HOT_CHANNEL_JSON = "FEMALE_HOT_CHANNEL_JSON";
	
	public static final String MALE_HOT_BANNER_JSON = "MALE_HOT_BANNER_JSON";
	
	public static final String FEMALE_HOT_BANNER_JSON = "FEMALE_HOT_BANNER_JSON";
	
	public static final String MALE_HOTLIST_JSON = "MALE_HOTLIST_JSON";
	
	public static final String FEMALE_HOTLIST_JSON = "FEMALE_HOTLIST_JSON";
	
	public static final String MALE_RANKING_CHANNEL_JSON = "MALE_RANKING_CHANNEL_JSON";
	
	public static final String FEMALE_RANKING_CHANNEL_JSON = "FEMALE_RANKING_CHANNEL_JSON";
	
	public static final String MALE_RANKINGLIST_JSON = "MALE_RANKINGLIST_JSON";
	
	public static final String FEMALE_RANKINGLIST_JSON = "FEMALE_RANKINGLIST_JSON";
	
	public static final String CLASSIFY_JSON = "CLASSIFY_JSON";
	
	public static final String CHANNEL_ALL = "0";//全部
			
	public static final String CHANNEL_MALE = "1";//精选男频
	
	public static final String CHANNEL_FEMALE = "2";//精选女频
	
	public static final String CHANNEL_MALE_RANKING = "3";//排行榜
	
	public static final String CHANNEL_FEMALE_RANKING = "4";//排行榜
	
	public static final String SEZRCH_HISTORY = "SEZRCH_HISTORY";//搜索历史
	
	public static final String RESOURCE_TYPE_NOVELREVIEW = "43";//小说type
	
	public static final int AUTO_CODE = 0;//搜索历史
	
	public static final int REQUEST_0 = 0;
	
	public static final int REQUEST_1 = 1;
	
	public static final int REQUEST_2 = 2;
	
	public static final int REQUEST_3 = 3;
	
	public static final int REQUEST_4 = 4;
	
	public static final int REQUEST_5 = 5;
	
	public static final int REQUEST_6 = 6;
	
	public static final String ADVERT_NOVEL_DETAILS = "40";//推广小说详情
	
	public static final String ADVERT_NOVEL_SUBJECT = "45";//推广专题
	
	public static final String ADVERT_NOVEL_WEB = "81";//推广外链
	
	public static final String COMPOUNDBUTTON = "COMPOUNDBUTTON";//推送按钮
	
	public static final int REQUEST_VERSION = 92;//检测新版本
	
	public static final int REQUEST_ADVERT = 93;//广告
	
	public static final int REQUEST_WX_OR_ALI_OR_QQ_NOTIFY = 94;//微信支付通知服务器
	
	public static final int REQUEST_WX = 95;//微信支付
	
	public static final int REQUEST_ZFB = 96;//支付宝支付
	
	public static final int REQUEST_CMB = 90;//招商银行支付
	
	public static final int REQUEST_QQ = 91;//QQ钱包支付
	
	public static final int REQUEST_LOGIN = 97;//登陆
	
	public static final int REQUEST_REGIEST = 98;//注册
	
	public static final int REQUEST_COMMENT_NUM = 99;//轮询请求未读评论数
	
	public static final int REQUEST_COMMENT = 100;//请求评论
	
	public static final int REQUEST_LIKE = 101;//请求稀饭
	
	public static final int REQUEST_MORE_COMMENT = 102;//请求更多评论
	
	public static final int REQUEST_DELETE = 103;//删除自身小说评的评论
	
	public static final int SELECT_CAMERA = 0;//拍照
	
	public static final int SELECT_CHOOSER = 1;//相册
	
	public static final String ALL = "ALL";//浏览历史表
	
	public static final int READ = 200;
	
	public static final String READ_GUIDANCE = "READ_GUIDANCE";//引导页显示标识
	
	public static final String BOOK_SHELF_MODE = "BOOK_SHELF_MODE";

	public static final  String AUTO_PAY = "AUTO_PAY";//自动购买
	
	public static final String VOLUME = "VOLUME";//音量翻页

	public static final String BRIGHTNESS = "BRIGHTNESS";//亮度调节
	
	public static final String MESSAGE_COMMENT = "先让大家认识你再来评论吧!";
	
	public static final String MESSAGE_RECHARGE = "为了防止您的火星币丢失,请先登陆之后再进行充值操作";
	
	public static final String MESSAGE_COMMENT_CANCLE = "那算了"; 
	
	public static final String MESSAGE_RECHARGE_CANCLE = "我知道了"; 
	
	public static final int PRIZE = 186;
	
//	public static final int[] TEXT_COLORS = new int[]{
//		R.color.mThemeTextColor,
//		R.color.mThemeTextColor,
//		R.color.mThemeTextColor,
//		R.color.mThemeTextColor,
//		R.color.night_txt_color
//	};
//
//	public static final int[] BACKGROUND_RES = new int[]{
//		R.drawable.color1_bg,
//		R.drawable.color2_bg,
//		R.drawable.color3_bg,
//		R.drawable.color4_bg,
//		R.color.night_txt_bg
//	};
//
//	public static final int[] TOPBAR_BACKGROUND_RES = new int[]{
//		R.drawable.color1_bg,
//		R.drawable.color2_bg,
//		R.drawable.color3_bg,
//		R.drawable.top_icn,
//		R.color.night_txt_bg
//	};
//
//	public static final int[] BOTTOMBAR_BACKGROUND_RES = new int[]{
//		R.drawable.color1_bg,
//		R.drawable.color2_bg,
//		R.drawable.color3_bg,
//		R.drawable.bottom_icn,
//		R.color.night_txt_bg
//	};

}
