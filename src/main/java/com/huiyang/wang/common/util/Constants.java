package com.huiyang.wang.common.util;

/**
 * 
 * @author 王辉阳
 * @date 2017年7月4日 下午3:01:32
 * @Description 常量
 */
public interface Constants {
	// DATE & TIME
	interface TIME {
		int MIN = 60;// 1小时3600秒
		int MIN15 = MIN * 15;// 1刻900秒
		int HALFHOUR = MIN15 * 2;// 半小时1800秒
		int HOUR = HALFHOUR * 2;// 1小时3600秒
		int DAY = HOUR * 24;// 1天24小时
		int WEEK = DAY * 7;// 1周7天
		int MONTH = DAY * 30;// 1周30天
	}

	String OPERATION_LOGGER = "operation.logger";
	String ACTION_SUFIX = ".do";
	String EMPTY_STR = "";
	String COMMA = ",";
	String SEMICOLON = ":";
	String LIST_DELIMITER = ",";

	String METHOD_OK = "";

	int DEFAULT_PG_SIZE = 10;

	String desKey = "password";

	/**
	 * 缓存键前缀
	 */
	interface CacheKey {
		String globalKey = "GLOBAL."; // 全局
		String accessTokenKey = "ACCESS_TOKEN."; // AccessToken
		String bindInfoKey = "BindInfo."; // 绑定相关信息
	}

	/**
	 * 异常代码
	 */
	interface ExceptionCode {
		String NOT_OWNER = "NotOwner";
	}

}