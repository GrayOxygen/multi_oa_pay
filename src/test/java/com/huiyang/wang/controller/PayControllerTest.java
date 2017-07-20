package com.huiyang.wang.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.validation.BindingResult;

import com.huiyang.wang.controller.weixin.PayController;

@TestPropertySource("/application.properties")
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class PayControllerTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private PayController payController;

	private BindingResult mockBindingResult;

	private MockHttpServletRequest req;
	private MockHttpServletResponse res;

	@Before
	public void setUp() throws Exception {
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
	}

	@Test
	public void ttt() {
		payController.successPayStatus("5e5d1c313716498bb64ed66858c76636", res);
	}

}
