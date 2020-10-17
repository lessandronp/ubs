package br.com.lessandro.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.UserCredentialDto;
import br.com.lessandro.dto.UserDto;
import br.com.lessandro.model.User;
import br.com.lessandro.repository.IUserRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IUserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

	private static String userId;

	@Autowired
	IUserService userService;

	@Autowired
	IUserRepository userRepository;

	@Test
	@Order(1)
	public void testAddUser() {
		try {
			UserCredentialDto userCredential = new UserCredentialDto(null, "Usuário Junit Jupiter", "usuariojupiter@teste.com.br",
					"usuario.jupiter", "123");
			UserDto userDto = userService.addUser(userCredential);
			userId = String.valueOf(userDto.getId());
			assertNotNull(userId);
		} catch (ValidationException e) {
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	@Order(2)
	public void testGetUsers() {
		try {
			PageDto<UserDto> page = userService.getAllUsers(0, 10);
			assertTrue(page.getContent().size() > 0);
		} catch (ValidationException e) {
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	@Order(3)
	public void testDeleteUser() {
		userRepository.deleteById(Long.parseLong(userId));
		Optional<User> opUser = userRepository.findById(Long.parseLong(userId));
		assertTrue(!opUser.isPresent());
	}

}
