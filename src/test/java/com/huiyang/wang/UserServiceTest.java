package com.huiyang.wang;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.huiyang.wang.model.User;
import com.huiyang.wang.service.UserService;

/**
 * 
 * @author 王辉阳
 * @date 2017年6月28日 上午3:57:32
 * @Description 测试CRUD方法
 */
//按照字母升序顺序测试
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
//设置放在非classpath下的配置文件
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private UserService userService;
	private static String id = null;

	@Test
	public void test_1_create() {
		User user = new User();
		id = UUID.randomUUID().toString().replaceAll("-", "");
		user.setId(id);
		user.setName("王先生");
		user.setAge(28);
		userService.add(user);
	}

	@Test
	public void test_2_retrieve() {
		User user = userService.getUser(id);
		System.out.println(user);
		assertNotNull(user);
	}

	@Test
	public void test_3_update() {
		User user = userService.getUser(id);
		user.setName("修改后的姓名");
		user.setAge(30);
		userService.update(user);
		System.out.println(userService.getUser(id));
	}

	@Test
	public void test_4_delete() {
		userService.delete(id);
		System.out.println(userService.getUser(id));
		assertNull(userService.getUser(id));
	}

}
