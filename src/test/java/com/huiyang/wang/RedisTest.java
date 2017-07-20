package com.huiyang.wang;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@SuppressWarnings("all")
// 按照字母升序顺序测试
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class RedisTest extends AbstractJUnit4SpringContextTests {
	@Resource(name = "redisTemplate")
	private RedisTemplate redisTemplate;

	// inject the template as ListOperations
	// can also inject as Value, Set, ZSet, and HashOperations
	@Resource(name = "redisTemplate")
	private ValueOperations valuesOpts;

	@Test
	public void testRedis_1_Add() {
		// appid-lastest token-""
		valuesOpts.append("appid_1", "xppp");
		valuesOpts.append("appid_2", "dddd");
		redisTemplate.boundValueOps("appid_1").expire(1, TimeUnit.MINUTES);
	}

	@Test
	public void testRedis_2_Get() {
		Object token_1 = valuesOpts.get("appid_1");
		String token_2 = (String) valuesOpts.get("appid_2");
		System.out.println(token_1);
		System.out.println(token_2);
	}
}
