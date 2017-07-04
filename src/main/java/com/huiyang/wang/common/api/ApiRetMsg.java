package com.huiyang.wang.common.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局返回码，每次调用接口时，可能获得正确或错误的返回码，开发者可以根据返回码信息调试接口，排查错误。
 * 
 */
public class ApiRetMsg {

	private static final Map<String, String> msgs = new HashMap<String, String>();

	public static final String SUCCESS = "0";
	public static final String ERR_NORMAL = "1";
	public static final String ERR_PARAM = "2";
	public static final String NOT_FOUND = "3";
	public static final String PERMISSION_DENIED = "4";
	public static final String ERR_SYSTEM = "5";
	public static final String ERR_WALL_NOT_CONFIG = "6";
	// wewayAccount
	public static final String ERR_NO_WEWAYACCOUNT = "10";
	public static final String ERR_IP_UNAUTHORIZED = "11";
	public static final String ERR_UNKNOWN_PKGNAME = "12";
	public static final String ERR_INVALID_MOBILE = "13";

	public static final String ERR_OFFICIAL_ACCOUNT_NOT_SET = "137";
	public static final String ERR_WEIXIN_SERVICE = "138";
	public static final String ERR_INVALID_FILE_SIZE = "139";
	public static final String ERR_INVALID_USER_OR_PASSWORD = "140";
	public static final String ERR_WALL_NOT_SET = "141";
	public static final String ERR_BEACON_POLL_NOT_SET = "142";
	public static final String ERR_BEACON_POLL_LOW = "143";
	public static final String ERR_WEIXIN_GET_MENU = "144";
	public static final String ERR_WEIXIN_DELETE_MENU = "145";
	public static final String ERR_WEIXIN_CREATE_MENU = "146";
	public static final String ERR_WEIXIN_GET_MATERIAL_COUNT = "147";
	public static final String ERR_WEIXIN_BATCH_GET_MATERIAL = "148";
	public static final String ERR_EMPTY_BEACONS_LOW_TWO = "149";
	public static final String ERR_WALL_BACKGROUND_URL = "151";
	public static final String ERR_WALL_BACKGROUND_NOT_CONFIG = "152";
	public static final String ERR_BEACON_UNKNOWN = "153";
	public static final String ERR_GET_WEIXIN_BEACON_INFO = "154";
	public static final String ERR_WEIXIN_EXCEPTION = "155";
	public static final String ERR_CHARGE_ORDER_UNKNOWN = "156";
	public static final String ERR_CODE = "157";

	public static final String STOCK_OVER = "600";
	public static final String NON_DRAW = "601";
	public static final String ERR_NON_PRIZE = "602";

	// 数据格式错误
	public static final String ERR_USER_MOBILE_EMPTY = "20001";
	public static final String ERR_USER_USERNAME_EMPTY = "20002";
	public static final String ERR_USER_PASSWORD_EMPTY = "20003";
	public static final String ERR_USER_USERNAME_HAD_EXISTED = "20004";
	public static final String ERR_USER_MOBILE_HAD_EXISTED = "20005";
	public static final String ERR_APPID_EMPTY = "20007";
	public static final String ERR_APPSECRET_EMPTY = "20008";
	public static final String ERR_IP_EMPTY = "20009";
	public static final String ERR_EMAIL = "20011";// 邮箱格式错误
	public static final String ERR_BANK_ACC = "20012";// 银行账号格式错误

	// 业务相关
	public static final String ERR_USER_NOT_LOGIN = "40000";
	public static final String ERR_USER_NOT_EXIST = "40001";
	public static final String ERR_PASSWORD_NOT_RIGHT = "40002";
	public static final String ERR_PASSWORD_NOT_MATCH_RULE = "40003";
	public static final String ERR_SEND_VERIFY_CODE = "40004";
	public static final String ERR_MOBILE_NUMBER_ILLEGAL = "40005";
	public static final String ERR_VERIFY_CODE = "40006";
	public static final String ERR_CLIENT_HAD_LOGIN = "40007";
	public static final String ERR_FAN_NOT_EXIST = "40008";
	public static final String ERR_BARRAGE_NOT_EXIST = "40009";
	public static final String ERR_PLAYLIST_NOT_EXIST = "40010";
	public static final String ERR_PARSE_JSON = "40011";
	public static final String ERR_UNKNOWN_PLAYLIST_NOT_ALLOW_LEAVE_BARRAGE = "40012";
	public static final String ERR_PROGRAM_HAD_CHANGED = "40013";
	public static final String ERR_SESSION_EXPIRED = "40014";
	public static final String ERR_USER_LOCKED = "40015";
	public static final String ERR_USER_EXPIRED = "40016";
	public static final String ERR_UNKNOWN_PROGRAM = "40017";
	public static final String ERR_BARRAGE_NOT_ALLOW = "40018";
	public static final String ERR_BUILD_IN_USER_FORBIDDEN_DELETE = "40019";
	public static final String ERR_CHARGE_TYPE_UNSUPPORTED = "40020";
	public static final String ERR_RED_PACK_AMOUNT = "40021";
	public static final String ERR_USER_LOGIN = "40022";
	public static final String ERR_BARRAGE_OFF = "40023";
	public static final String ERR_USER_STATUS_EXPIRED_NOT_ALLOW_LOCK = "40024";
	public static final String ERR_USER_STATUS_EXPIRED_NOT_ALLOW_UNLOCK = "40025";
	public static final String ERR_PROGRAM_UNKNOWN = "40026";
	public static final String ERR_PROGRAM_HAS_START = "40027";
	public static final String ERR_NO_PROGRAM = "40028";
	public static final String ERR_BALANCE_LACK = "40029";
	public static final String ERR_BALANCE_RESET = "40030";
	public static final String ERR_USERPRIZE_NOT_EXISTS = "40031";
	public static final String ERR_NOT_CHARGE = "40032";
	public static final String ERR_DUPLI_SCRATCH = "40033";
	public static final String ERR_LACK_VIBEAN = "40034";
	public static final String ERR_MUST_PAY = "40035";
	public static final String ERR_SCRASH_OVER = "40036";
	public static final String ERR_PRIZE_EXISTS = "40037";

	public static final String ERR_PROGNAME_TOO_LONG = "40038";
	public static final String ERR_LOGONAME_TOO_LONG = "40039";
	public static final String ERR_ORGNAME_TOO_LONG = "40040";
	public static final String ERR_TOPTITLENAME_TOO_LONG = "40041";

	public static final String ERR_PROGNAME_NULL = "40042";
	public static final String ERR_LOGONAME_NULL = "40043";
	public static final String ERR_ORGNAME_NULL = "40044";
	public static final String ERR_TOPTITLENAME_NULL = "40045";
	public static final String ERR_APPLY_FORM_EXISTS = "40046";
	public static final String ERR_APPLY_FORM_NULL = "40047";

	// DYl
	public static final String ERR_UPLOAD_IMG = "50013";

	public static final String ERR_UNBIND_DEVICE = "50015";
	public static final String ERR_GET_TOKEN = "50018";

	public static final String ERR_REQUEST_FAILURE = "50019";

	static {
		msgs.put(SUCCESS, "请求成功");
		msgs.put(ERR_NORMAL, "系统内部错误");
		msgs.put(ERR_PARAM, "参数错误");
		msgs.put(NOT_FOUND, "请求地址或资源未找到");
		msgs.put(PERMISSION_DENIED, "未通过权限验证");
		msgs.put(ERR_SYSTEM, "系统异常");
		msgs.put(ERR_WALL_NOT_CONFIG, "签名墙未配置");
		msgs.put(ERR_OFFICIAL_ACCOUNT_NOT_SET, "公众号未设置");
		msgs.put(ERR_WEIXIN_SERVICE, "微信服务异常");
		msgs.put(ERR_INVALID_FILE_SIZE, "图片尺寸不对");
		msgs.put(ERR_INVALID_USER_OR_PASSWORD, "用户不存在或密码错误");

	}

	public static String getMsg(final String code) {
		return msgs.get(code);
	}

}
