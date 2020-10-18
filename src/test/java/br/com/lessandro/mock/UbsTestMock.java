package br.com.lessandro.mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.lessandro.service.IUbsService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class UbsTestMock {

	@Autowired
	IUbsService ubsService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	public void testGetAllUbs() {
		try {
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.get("/api/v1/allUbs").with(user("user.teste")).with(csrf())
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();
			String resultDOW = result.getResponse().getContentAsString();
			assertNotNull(resultDOW);
		} catch (Exception e) {
			Assertions.fail(e.getMessage());
		}
	}

}
