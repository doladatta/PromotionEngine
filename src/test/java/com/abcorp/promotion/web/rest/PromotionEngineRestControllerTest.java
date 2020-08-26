package com.abcorp.promotion.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class PromotionEngineRestControllerTest {
	
	private MockMvc mockMvc;
	
	private static final ObjectMapper mapper = createObjectMapper();

	@InjectMocks
	private PromotionEngineRestController promotionEngineRestController;


	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(promotionEngineRestController)
				.build();
	}

	@Test
	void testApplyPromotionsToCart() throws IOException, Exception {
		
		// Given
		Map<String, Integer> request = new HashMap<String, Integer>();
		// When
			// TBD
		// Then
		mockMvc.perform(post("/cart/promotions/apply")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(request )))
				.andExpect(status().isOk());
	}
	
	private static ObjectMapper createObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper;
	}

	private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		return mapper.writeValueAsBytes(object);
	}


}
