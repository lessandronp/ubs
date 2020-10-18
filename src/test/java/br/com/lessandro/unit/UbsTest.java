package br.com.lessandro.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.UbsDto;
import br.com.lessandro.dto.UserDto;
import br.com.lessandro.model.User;
import br.com.lessandro.repository.IUbsRepository;
import br.com.lessandro.repository.IUserRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.security.UserPrincipal;
import br.com.lessandro.service.IUbsService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UbsTest {

	private static UserPrincipal currentUser;

	@Autowired
	IUbsService ubsService;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IUbsRepository ubsRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Test
	@Order(1)
	public void testGetAllUbs() {
		try {
			currentUser = prepareUserAndRoles();
			UserDto userDto = modelMapper.map(currentUser, UserDto.class);
			PageDto<UbsDto> pageUbs = ubsService.getAllUbs(10, 3);
			assertNotNull(userDto.getId());
			assertTrue(!pageUbs.getContent().isEmpty());
		} catch (ValidationException e) {
			Assertions.fail(e.getMessage());
		}
	}

	private UserPrincipal prepareUserAndRoles() {
		Optional<User> opUser = userRepository.findByUsername("user.teste");
		if (opUser.isPresent()) {
			UserPrincipal currentUser = UserPrincipal.create(opUser.get());
			return currentUser;
		}
		return null;
	}

	@Test
	@Order(2)
	public void testGetGeocode() {
		try {
			PageDto<UbsDto> pageUbs = ubsService.findTopUbsByGeocode("-23.604936,-46.692999", 0, 3);
			assertTrue(!pageUbs.getContent().isEmpty());
			assertTrue(pageUbs.getContent().get(0).getGeocode().getLatitude().equals("-23.6099946498864"));
			assertTrue(pageUbs.getContent().get(0).getGeocode().getLongitude().equals("-46.7057347297655"));
		} catch (ValidationException e) {
			Assertions.fail(e.getMessage());
		}
	}

}
