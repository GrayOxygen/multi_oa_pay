package com.huiyang.wang.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by duyonglin on 2015/11/2.
 */
public class StringUtil {

	public static String[] str2arr(String str, String regex) {
		if (str != null && str != "") {
			String[] arr = str.split(regex);
			return arr;
		}
		return null;
	}

	public static String arr2str(List<String> arr, String regex) {
		StringBuilder temp = new StringBuilder();
		if (arr != null && arr.size() > 0) {
			/*
			 * for (int i = 0; i < arr.size(); i++) { result.append(arr.get(i));
			 * if (i < arr.size() - 1) { result.append(regex); } }
			 */
			for (String str : arr) {
				temp.append(str);
				temp.append(regex);
			}
			String result = temp.toString();
			return result.substring(0, result.length() - 1);
		}
		return null;
	}

	/**
	 * list的值用逗号隔开构成一个字符串，逗号隔开的顺序与list中的值顺序一致
	 * 
	 * @author 王辉阳
	 * @date 2015年8月14日 下午1:03:04
	 * @param list
	 *            需要转换的值
	 * @return 逗号隔开的字符串
	 */
	public static String listToStr(List<String> list) {
		if (list == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				sb.append(list.get(i));
			} else {
				sb.append(list.get(i) + ",");
			}
		}
		return sb.toString();
	}

	/**
	 * set的值用逗号隔开构成一个字符串，無序隔開
	 * 
	 * @author 王辉阳
	 * @date 2015年8月14日 下午1:03:04
	 * @param set
	 *            需要转换的值
	 * @return 逗号隔开的字符串
	 */
	public static String setToStr(Set<String> set) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = set.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			String temp = (String) it.next();
			if (index == set.size()) {
				sb.append(temp);
			} else {
				sb.append(temp + ",");
			}

		}
		return sb.toString();
	}

	/**
	 * 
	 * @author 王辉阳
	 * @date 2015年10月13日 下午3:44:45
	 * @param str
	 *            "12,34" 逗号隔开的字符串
	 * @return 转换为字符串list
	 */
	public static List<String> strToList(String str) {
		List<String> list = new ArrayList<String>();
		if (str == null || StringUtils.isBlank(str)) {
			return null;
		}
		if (!str.contains(",")) {
			list.add(str);
			return list;
		}

		String[] array = str.split(",");

		for (String temp : array) {
			list.add(temp);
		}

		return list;
	}

	/**
	 * 
	 * @author 王辉阳
	 * @date 2015年10月16日 下午3:32:39
	 * @param list
	 * @param propertyName
	 * @return 参数为空返回为null
	 */
	public static <T> List<String> toStrList(List<T> list, String propertyName) {
		if (list == null || list.size() < 1 || propertyName == null) {
			List<String> destList = new ArrayList<String>();
			destList.add("");
			return destList;
		}

		List<String> destList = new ArrayList<String>(list.size());

		try {

			for (T temp : list) {
				destList.add(String.valueOf(BeanUtils.getProperty(temp, propertyName)));
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		return destList;

	}
}
