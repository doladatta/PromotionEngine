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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.service.PromotionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class PromotionEngineRestControllerTest {
	
	private MockMvc mockMvc;
	
	private static final ObjectMapper mapper = createObjectMapper();

	@InjectMocks
	private PromotionEngineRestController promotionEngineRestController;
	
	@Autowired
	PromotionServiceImpl promotionService;


	@BeforeEach
	void setUp() throws Exception {
		ReflectionTestUtils.setField(promotionEngineRestController, "promotionService", promotionService);
		mockMvc = MockMvcBuilders.standaloneSetup(promotionEngineRestController)
				.build();
	}

	@Test
	void testApplyPromotionsToCart1() throws IOException, Exception {
		
		// Given
		Map<String, Integer> cartItems = new HashMap<String, Integer>();
		cartItems.put("A", 1);
		cartItems.put("B", 1);
		cartItems.put("C", 1);
		Cart cart = new Cart();
		cart.setCart(cartItems);
		Double expectedValue = 100.0;
		// When
			// TBD
		// Then
		mockMvc.perform(post("/cart/promotions/apply")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(cart)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value").exists())
				.andExpect(jsonPath("$.value").value(expectedValue));
	}
	
	@Test
	void testApplyPromotionsToCart2() throws IOException, Exception {
		
		// Given
		Map<String, Integer> cartItems = new HashMap<String, Integer>();
		cartItems.put("A", 5);
		cartItems.put("B", 5);
		cartItems.put("C", 1);
		Cart cart = new Cart();
		cart.setCart(cartItems);
		Double expectedValue = 370.0;
		// When
			// TBD
		// Then
		mockMvc.perform(post("/cart/promotions/apply")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(cart)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value").exists())
				.andExpect(jsonPath("$.value").value(expectedValue));
	}
	
	@Test
	void testApplyPromotionsToCart3() throws IOException, Exception {
		
		// Given
		Map<String, Integer> cartItems = new HashMap<String, Integer>();
		cartItems.put("A", 3);
		cartItems.put("B", 5);
		cartItems.put("C", 1);
		cartItems.put("D", 1);
		Cart cart = new Cart();
		cart.setCart(cartItems);
		Double expectedValue = 280.0;
		// When
			// TBD
		// Then
		mockMvc.perform(post("/cart/promotions/apply")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(cart)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value").exists())
				.andExpect(jsonPath("$.value").value(expectedValue));
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
