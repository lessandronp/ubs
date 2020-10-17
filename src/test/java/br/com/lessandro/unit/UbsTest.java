package br.com.lessandro.unit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.lessandro.repository.IUbsRepository;
import br.com.lessandro.repository.IUserRepository;
import br.com.lessandro.security.UserPrincipal;
import br.com.lessandro.service.IUbsService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UbsTest {
	
	private static String postId;
	private static UserPrincipal currentUser;

	@Autowired
	IUbsService ubsService;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IUbsRepository ubsRepository;

	@Autowired
	private ModelMapper modelMapper;

//	@Test
//	@Order(1)
//	public void testAddPostWithImage() {
//		try {
//			currentUser = prepareUserAndRoles();
//			UserDto userDto = modelMapper.map(currentUser, UserDto.class);
//			List<ImageDto> images = new ArrayList<>();
//			images.add(new ImageDto(null, "Image Test", "http://www.teste.com.br/img.png"));
//			PostDto postDto = new PostDto(null, "Post teste", "Post teste content", userDto, new ArrayList<>(),
//					images, new ArrayList<>());
//			ResponseEntity<PostDto> response = postService.addPost(postDto, currentUser);
//			postId = String.valueOf(response.getBody().getId());
//			assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
//			assertNotNull(postId);
//		} catch (ValidationException e) {
//			Assertions.fail(e.getMessage());
//		}
//	}
//
//	private UserPrincipal prepareUserAndRoles() {
//		Optional<User> opUser = userRepository.findByUsername("user.teste");
//		if (opUser.isPresent()) {
//			UserPrincipal currentUser = UserPrincipal.create(opUser.get());
//			return currentUser;
//		}
//		return null;
//	}
//
//	@Test
//	@Order(2)
//	public void testCheckPostsWithImage() {
//		checkPost();
//	}
//
//	private void checkPost() {
//		try {
//			PageDto<PostDto> page = postService.getAllPosts(0, 10);
//			assertTrue(page.getContent().size() > 0);
//		} catch (ValidationException e) {
//			Assertions.fail(e.getMessage());
//		}
//	}
//	
//	private void deletePost() {
//		try {
//			ResponseEntity<?> response = postService.deletePost(postId, currentUser);
//			Optional<Post> opPost = postRepository.findById(Long.parseLong(postId));
//			assertTrue(!opPost.isPresent());
//			assertTrue(response.getStatusCode().equals(HttpStatus.OK));
//		} catch (ValidationException e) {
//			Assertions.fail(e.getMessage());
//		}
//	}
//
//	@Test
//	@Order(3)
//	public void testDeletePostWithImage() {
//		deletePost();
//	}
//	
//	@Test
//	@Order(4)
//	public void testAddPostWithLink() {
//		try {
//			currentUser = prepareUserAndRoles();
//			UserDto userDto = modelMapper.map(currentUser, UserDto.class);
//			List<LinkDto> links = new ArrayList<>();
//			links.add(new LinkDto(null, "http://semprejava.wordpress.com"));
//			PostDto postDto = new PostDto(null, "Post teste", "Post teste content", userDto, new ArrayList<>(),
//					new ArrayList<>(), links);
//			ResponseEntity<PostDto> response = postService.addPost(postDto, currentUser);
//			postId = String.valueOf(response.getBody().getId());
//			assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
//			assertNotNull(postId);
//		} catch (ValidationException e) {
//			Assertions.fail(e.getMessage());
//		}
//	}
//	
//	@Test
//	@Order(5)
//	public void testCheckPostsWithLink() {
//		checkPost();
//	}
//
//	@Test
//	@Order(6)
//	public void testDeletePostWithLink() {
//		deletePost();
//	}
}
