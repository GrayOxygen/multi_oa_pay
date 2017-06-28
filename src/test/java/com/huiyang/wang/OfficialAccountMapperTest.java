package com.huiyang.wang;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.huiyang.wang.common.entity.enums.PayType;
import com.huiyang.wang.mapper.OfficialAccountMapper;
import com.huiyang.wang.model.OfficialAccount;

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
public class OfficialAccountMapperTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private OfficialAccountMapper officialAccountMapper;
	private static String id = null;

	@Test
	public void test_1_create() {
		OfficialAccount oa = new OfficialAccount();
		oa.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		oa.setAppId("yyy");
		oa.setMchId("xxx");
		oa.setKey("dfsdf");
		oa.setCtime(new Date());
		oa.setName("测试公众号");
		oa.setSecret("fdf");
		oa.setToken("aaaa");
		oa.setType(PayType.PTS);
		officialAccountMapper.save(oa);
		id = oa.getId();
	}

	@Test
	public void test_2_get() {
		Assert.assertNotNull(officialAccountMapper.get(id));
	}

	@Test
	public void test_3_update() {
		OfficialAccount oa = officialAccountMapper.get(id);
		oa.setName("修改了的名字");
		officialAccountMapper.update(oa);
		Assert.assertNotNull(officialAccountMapper.get(id));
	}

	@Test
	public void test_4_delete() {
		officialAccountMapper.batchSoftOpt(true, Arrays.asList(id));
		Assert.assertEquals(true, officialAccountMapper.get(id).isDeleted());
	}

}
