package com.cm.catalog.dto;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import org.junit.Test;

public class ProductTest {
	@Test
	public void testCreateFromJSON() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"id\":\"/products/3\",\"category\":\"/categories/1\",\"internalName\":\"streeterLeatherSwivelGlider\",\"displayName\":\"Streeter Leather Swivel Glider\",\"price\":\"1499.00\",\"active\":\"true\"}";
		
		ObjectMapper mapper = new ObjectMapper();
		
		Product p = mapper.readValue(json, Product.class);
		
		assertNotNull(p.getCategory());
		assertNotNull(p.getDisplayName());
		assertNotNull(p.getPrice());
		assertNotNull(p.getId());
		assertNotNull(p.getInternalName());
		
		
	}
	
	@Test
	public void testCreateFromJSONAdditionFields() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"id\":\"/products/3\",\"key\":\"xxxxx\",\"category\":\"/categories/1\",\"internalName\":\"streeterLeatherSwivelGlider\",\"displayName\":\"Streeter Leather Swivel Glider\",\"price\":\"1499.00\",\"active\":\"true\"}";
		
		ObjectMapper mapper = new ObjectMapper();
		
		Product p = mapper.readValue(json, Product.class);
		
		assertNotNull(p.getCategory());
		assertNotNull(p.getDisplayName());
		assertNotNull(p.getPrice());
		assertNotNull(p.getId());
		assertNotNull(p.getInternalName());
		assertTrue(p.isActive());
	}
	
	@Test
	public void testCreate_IsActiveFalse() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"id\":\"/products/3\",\"key\":\"xxxxx\",\"category\":\"/categories/1\",\"internalName\":\"streeterLeatherSwivelGlider\",\"displayName\":\"Streeter Leather Swivel Glider\",\"price\":\"1499.00\",\"active\":\"false\"}";
		
		ObjectMapper mapper = new ObjectMapper();
		
		Product p = mapper.readValue(json, Product.class);
		
		assertNotNull(p.getCategory());
		assertNotNull(p.getDisplayName());
		assertNotNull(p.getPrice());
		assertNotNull(p.getId());
		assertNotNull(p.getInternalName());
		assertFalse(p.isActive());
	}
}
