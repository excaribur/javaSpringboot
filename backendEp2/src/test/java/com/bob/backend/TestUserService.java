package com.bob.backend;

import com.bob.backend.entity.User;
import com.bob.backend.exception.BaseException;
import com.bob.backend.exception.UserException;
import com.bob.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
		User user = userService.create(
				TestCreateData.email,
				TestCreateData.password,
				TestCreateData.name
		);

		// check not null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		// check equal
		Assertions.assertEquals(TestCreateData.email, user.getEmail());

		boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);

		Assertions.assertEquals(TestCreateData.name, user.getName());
	}

	@Order(2)
	@Test
	void testUpdate() throws UserException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertNotNull(TestUpdateData.name, updatedUser.getName());

	}

	@Order(3)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		userService.deleteById(user.getId());

		Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(optDelete.isEmpty());
	}

	interface TestCreateData {
		String email = "bob@test.com";

		String password = "m1234E@1234";

		String name = "Jirayut Phonyiam";
	}

	interface TestUpdateData {

		String name = "Bob Swagger";

	}


}
