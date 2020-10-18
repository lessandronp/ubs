package br.com.lessandro.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

	private static String token = "";

	@Test
	@Order(1)
	public void testGetAllUbs() {
		try {
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.get("/api/v1/allUbs").with(user("user.teste")).with(csrf())
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn();
			JSONObject jsonGeocode = prepareJsonGeocode(result);
			assertEquals(jsonGeocode.get("latitude"), "-9.9162447452542");
			assertEquals(jsonGeocode.get("longitude"), "-36.3566136360158");
		} catch (Exception e) {
			Assertions.fail(e.getMessage());
		}
	}

	private JSONObject prepareJsonGeocode(MvcResult result) throws UnsupportedEncodingException, JSONException {
		JSONObject jsonResponse = prepareJsonResponse(result);
		JSONArray contentArray = jsonResponse.getJSONArray("content");
		JSONObject jsonUbs = (JSONObject) contentArray.get(0); 
		JSONObject jsonGeocode = jsonUbs.getJSONObject("geocode");
		return jsonGeocode;
	}

	@Test
	@Order(2)
	public void testAuthenticate() {
		try {
			String body = "{ \"username\" : \"user.teste\", \"password\" : \"123\" }";
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.post("/api/v1/users/authenticate")
							.content(body)
							.with(csrf()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn();
			JSONObject jsonResponse = prepareJsonResponse(result);
			token = jsonResponse.getString("accessToken");
			assertNotNull(token);
		} catch (Exception e) {
			Assertions.fail(e.getMessage());
		}
	}

	private JSONObject prepareJsonResponse(MvcResult result) throws UnsupportedEncodingException, JSONException {
		String response = result.getResponse().getContentAsString();
		JSONObject jsonResponse = new JSONObject(response);
		return jsonResponse;
	}

	@Test
	@Order(3)
	public void testGetGeocode() {
		try {
			MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.get("/api/v1/find_ubs?query=-23.604936,-46.692999&page=0&per_page=3")
									.header("Authorization", "Bearer " + token).with(user("user.teste")).with(csrf())
									.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn();
			JSONObject jsonGeocode = prepareJsonGeocode(result);
			assertEquals(jsonGeocode.get("latitude"), "-23.6099946498864");
			assertEquals(jsonGeocode.get("longitude"), "-46.7057347297655");
		} catch (Exception e) {
			Assertions.fail(e.getMessage());
		}
	}

}
