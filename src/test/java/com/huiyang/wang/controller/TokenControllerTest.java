package com.huiyang.wang.controller;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.validation.BindingResult;

import com.huiyang.wang.controller.weixin.common.TokenController;

@TestPropertySource("/application.properties")
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class TokenControllerTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private TokenController tokenController;
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;

	private BindingResult mockBindingResult;

	private MockHttpServletRequest req;
	private MockHttpServletResponse res;

	@Before
	public void setUp() throws Exception {
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
	}

	@Test
	public void getAccessToken() {
		String token = tokenController.accessToken("wx737ad133237892d7", "af7b6b5474fc4cba69682344500c1b98");
		System.out.println(token);
	}

	@Test
	public void getOauthToken() {
		//
		// String token = tokenController.oauthToken("wx737ad133237892d7",
		// "af7b6b5474fc4cba69682344500c1b98", code);
		// System.out.println(token);
	}

	@Test
	public void redisExpire() {
		redisTemplate.boundValueOps("tester").append("testtttt");
		redisTemplate.boundValueOps("tester").expire(10, TimeUnit.SECONDS);
		boolean flag = true;
		while (flag) {
			System.out.println(redisTemplate.boundValueOps("tester").get());
			System.out.println(redisTemplate.boundValueOps("tester").getExpire());
			System.out.println(redisTemplate.boundValueOps("tester").getKey());
			if (redisTemplate.boundValueOps("tester").getExpire() <= 0) {
				flag = false;
			}
		}
	}

}
