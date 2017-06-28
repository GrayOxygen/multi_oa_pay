package com.huiyang.wang;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.huiyang.wang.common.entity.enums.PayOrderStatus;
import com.huiyang.wang.mapper.PayOrderMapper;
import com.huiyang.wang.model.PayOrder;
import com.saysth.commons.utils.StrUtils;
import com.saysth.weixin.sdk.pay.enums.TradeType;

/**
 * 
 * @author 王辉阳
 * @date 2017年6月28日 上午3:57:32
 * @Description 测试CRUD方法
 */
// 按照字母升序顺序测试
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// 设置放在非classpath下的配置文件
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class PayMapperTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private PayOrderMapper payOrderMapper;
	private static String id = null;

	@Test
	public void test_1_create() {
		PayOrder order = new PayOrder();
		order.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		order.setAppId("32dfd");
		order.setMchId("fasfasdf");
		order.setSubAppId("12dd");
		order.setSubMchId("dfa");
		order.setTradeType(TradeType.JSAPI);
		order.setStatus(PayOrderStatus.CREATED);
		order.setNonceStr(StrUtils.getShortUUID());
		order.setAttach("测试");
		order.setBody("测试");
		order.setDetail("测试");
		order.setProductId("1");
		order.setSubOpenId("123");
		order.setTotalFee(123);
		order.setSpBillCreateIp("127.0.0.1");
		order.setNotifyUrl("baidu.com");
		order.setCtime(new Date());
		payOrderMapper.save(order);
		id = order.getId();
	}

	@Test
	public void test_2_get() {
		Assert.assertNotNull(payOrderMapper.get(id));
	}

	@Test
	public void test_3_update() {
		PayOrder order = payOrderMapper.get(id);
		order.setAttach("修改后的attach");
		order.setBankType("招商银行");
		payOrderMapper.update(order);
		Assert.assertNotNull(payOrderMapper.get(id));
	}

	@Test
	public void test_4_delete() {
		payOrderMapper.delete(id);
		Assert.assertNull(payOrderMapper.get(id));
	}

}
