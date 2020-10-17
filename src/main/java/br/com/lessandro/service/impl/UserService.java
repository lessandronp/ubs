package br.com.lessandro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.UserCredentialDto;
import br.com.lessandro.dto.UserDto;
import br.com.lessandro.model.Role;
import br.com.lessandro.model.RoleName;
import br.com.lessandro.model.User;
import br.com.lessandro.repository.IRoleRepository;
import br.com.lessandro.repository.IUserRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.security.UserPrincipal;
import br.com.lessandro.service.IUserService;
import br.com.lessandro.validator.PageValidator;

@Service
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	private PasswordEncoder passwordEncoder;

	@Override
	public PageDto<UserDto> getAllUsers(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "creationDate");
		Page<User> users = userRepository.findAll(pageable);
		if (users.getNumberOfElements() == 0) {
			return new PageDto<>(Collections.emptyList(), users.getNumber(), users.getSize(), users.getTotalElements(),
					users.getTotalPages(), users.isLast());
		}
		List<UserDto> usersDto = Arrays.asList(modelMapper.map(users.getContent(), UserDto[].class));
		return new PageDto<>(usersDto, users.getNumber(), users.getSize(), users.getTotalElements(),
				users.getTotalPages(), users.isLast());
	}

	@Override
	public UserDto getCurrentUser(UserPrincipal currentUser) {
		return new UserDto(currentUser.getId(), currentUser.getEmail(), currentUser.getUsername(),
				currentUser.getName());
	}

	@Override
	public UserDto addUser(UserCredentialDto userCredential) throws ValidationException {
		String usernameExists = String.format("Já existe um usuário com esse username: %s", userCredential.getUsername());
		if (userRepository.existsByUsername(userCredential.getUsername())) {
			throw new ValidationException("User", "username", HttpStatus.CONFLICT, usernameExists);
		}
		List<Role> roles = new ArrayList<>();
		String roleNotFound = String.format("Nenhuma role user cadastrada: %s", RoleName.ROLE_USER);
		roles.add(roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new ValidationException("Role", "name", HttpStatus.NOT_FOUND, roleNotFound)));
		User user = modelMapper.map(userCredential, User.class);
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException(
						String.format("O usuário informado não existe com esse login: %s", usernameOrEmail)));
		return UserPrincipal.create(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
				String.format("O usuário informado não existe com esse id: %s", id)));
		return UserPrincipal.create(user);
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
