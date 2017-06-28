package com.huiyang.wang.common.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局返回码，每次调用接口时，可能获得正确或错误的返回码，开发者可以根据返回码信息调试接口，排查错误。
 * 
 * @author KelvinZ
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
		msgs.put(ERR_WALL_NOT_SET, "未配置签名墙");
		msgs.put(ERR_BEACON_POLL_NOT_SET, "未配置信标池,请到微信周边后台配置信标池");
		msgs.put(ERR_BEACON_POLL_LOW, "信标池信标数量过少，必须2个");
		msgs.put(ERR_WEIXIN_GET_MENU, "微信后台获取菜单异常，请联系管理员");
		msgs.put(ERR_WEIXIN_DELETE_MENU, "微信后台删除菜单异常，请联系管理员");
		msgs.put(ERR_WEIXIN_CREATE_MENU, "微信后台创建菜单异常，请联系管理员");
		msgs.put(ERR_WEIXIN_GET_MATERIAL_COUNT, "微信后台获取素材总数异常，请联系管理员");
		msgs.put(ERR_WEIXIN_BATCH_GET_MATERIAL, "微信后台获取素材列表异常，请联系管理员");
		msgs.put(ERR_EMPTY_BEACONS_LOW_TWO, "信标池空闲信标过少");
		msgs.put(ERR_UPLOAD_IMG, "上传图片出错，请重新发布");
		msgs.put(ERR_WALL_BACKGROUND_URL, "错误签名墙背景URL");
		msgs.put(ERR_WALL_BACKGROUND_NOT_CONFIG, "签名墙背景URL未配置");
		msgs.put(ERR_BEACON_UNKNOWN, "无法识别的信标");
		msgs.put(ERR_GET_WEIXIN_BEACON_INFO, "获取微信信标异常");
		msgs.put(ERR_WEIXIN_EXCEPTION, "微信服务器异常");

		msgs.put(STOCK_OVER, "Sorry,您来晚啦！");
		msgs.put(NON_DRAW, "Sorry，您什么都没有抽中！");
		msgs.put(ERR_NON_PRIZE, "Sorry,奖品已发完！");

		// 数据错误
		msgs.put(ERR_USER_MOBILE_EMPTY, "手机号码为空");
		msgs.put(ERR_USER_USERNAME_EMPTY, "用户名为空");
		msgs.put(ERR_USER_PASSWORD_EMPTY, "密码为空");
		msgs.put(ERR_USER_USERNAME_HAD_EXISTED, "用户名已存在");
		msgs.put(ERR_USER_MOBILE_HAD_EXISTED, "手机号码已存在");
		msgs.put(ERR_INVALID_MOBILE, "手机号码不正确");
		msgs.put(ERR_CODE, "验证码错误");
		msgs.put(ERR_EMAIL, "邮箱格式不正确!");
		msgs.put(ERR_BANK_ACC, "银行账号格式不正确!");

		// 业务相关
		msgs.put(ERR_USER_NOT_LOGIN, "用户未登录");
		msgs.put(ERR_USER_NOT_EXIST, "用户不存在");
		msgs.put(ERR_PASSWORD_NOT_RIGHT, "密码不正确");
		msgs.put(ERR_PASSWORD_NOT_MATCH_RULE, "密码不合符规范");
		msgs.put(ERR_SEND_VERIFY_CODE, "发送验证码失败");
		msgs.put(ERR_MOBILE_NUMBER_ILLEGAL, "非法手机号码");
		msgs.put(ERR_VERIFY_CODE, "验证码不正确");
		msgs.put(ERR_CLIENT_HAD_LOGIN, "客户端已经登录");
		msgs.put(ERR_FAN_NOT_EXIST, "不存的微信粉丝");
		msgs.put(ERR_BARRAGE_NOT_EXIST, "不存的弹幕");
		msgs.put(ERR_PLAYLIST_NOT_EXIST, "清单已过时");
		msgs.put(ERR_PARSE_JSON, "JSON解析失败");
		msgs.put(ERR_UNKNOWN_PLAYLIST_NOT_ALLOW_LEAVE_BARRAGE, "未知节目清单,不允许弹幕");
		msgs.put(ERR_PROGRAM_HAD_CHANGED, "节目切换,请重新摇一摇");
		msgs.put(ERR_SESSION_EXPIRED, "身份信息已过期");
		msgs.put(ERR_USER_LOCKED, "账号已锁定");
		msgs.put(ERR_USER_EXPIRED, "账号已过期");
		msgs.put(ERR_UNKNOWN_PROGRAM, "未知的节目类型");
		msgs.put(ERR_BARRAGE_NOT_ALLOW, "你被拉黑,无法弹幕");
		msgs.put(ERR_BUILD_IN_USER_FORBIDDEN_DELETE, "内置用户无法删除");
		msgs.put(ERR_CHARGE_TYPE_UNSUPPORTED, "暂不支持的充值类型");
		msgs.put(ERR_CHARGE_ORDER_UNKNOWN, "不存在的充值订单");
		msgs.put(ERR_RED_PACK_AMOUNT, "红包金额必须在1元到200元之间");
		msgs.put(ERR_USER_LOGIN, "该账号已在另一台设备登录，此处已下线");
		msgs.put(ERR_BARRAGE_OFF, "弹幕关闭,无法发送弹幕");
		msgs.put(ERR_USER_STATUS_EXPIRED_NOT_ALLOW_LOCK, "用户已过期,无法锁定");
		msgs.put(ERR_USER_STATUS_EXPIRED_NOT_ALLOW_UNLOCK, "用户已过期,无法解锁");
		msgs.put(ERR_PROGRAM_UNKNOWN, "无法识别的节目");
		msgs.put(ERR_PROGRAM_HAS_START, "活动已开始,无法修改");
		msgs.put(ERR_NO_PROGRAM, "未找到活动");
		msgs.put(ERR_BALANCE_LACK, "余额不足");
		msgs.put(ERR_BALANCE_RESET, "余额解冻出错");
		msgs.put(ERR_NOT_CHARGE, "活动未支付");
		msgs.put(ERR_DUPLI_SCRATCH, "不能重复刮");
		msgs.put(ERR_LACK_VIBEAN, "抱歉，商家微豆不足");
		msgs.put(ERR_MUST_PAY, "此订单必须支付");
		msgs.put(ERR_SCRASH_OVER, "已刮完，谢谢");

		msgs.put(ERR_REQUEST_FAILURE, "微信接口异常");

		msgs.put(ERR_USERPRIZE_NOT_EXISTS, "奖品不存在");
		msgs.put(ERR_PRIZE_EXISTS, "奖品未发完，无法删除！");

		// wewayAccount
		msgs.put(ERR_NO_WEWAYACCOUNT, "appid or appscret is error ");
		msgs.put(ERR_IP_UNAUTHORIZED, "IP unauthorized");
		msgs.put(ERR_UNKNOWN_PKGNAME, "illegal pkgName");
		msgs.put(ERR_APPID_EMPTY, "appid must not null");
		msgs.put(ERR_APPSECRET_EMPTY, "appsecret must not null");
		msgs.put(ERR_IP_EMPTY, "ip must not null");

		msgs.put(ERR_PROGNAME_TOO_LONG, "活动名称长度不能超过32");
		msgs.put(ERR_LOGONAME_TOO_LONG, "logo标题长度不能超过32");
		msgs.put(ERR_ORGNAME_TOO_LONG, "组织名称长度不能超过32");
		msgs.put(ERR_TOPTITLENAME_TOO_LONG, "顶部标题长度不能超过32");

		msgs.put(ERR_PROGNAME_NULL, "活动名称不能为空");
		msgs.put(ERR_LOGONAME_NULL, "logo标题不能为空");
		msgs.put(ERR_ORGNAME_NULL, "组织名称不能为空");
		msgs.put(ERR_APPLY_FORM_EXISTS, "用户订单已存在，不能创建多个订单!");
		msgs.put(ERR_APPLY_FORM_NULL, "用户订单为空，请重新确认");

	}

	public static String getMsg(final String code) {
		return msgs.get(code);
	}

}
