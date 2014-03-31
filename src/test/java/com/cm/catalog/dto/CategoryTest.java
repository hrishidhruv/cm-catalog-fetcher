package com.cm.catalog.dto;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import org.junit.Test;

public class CategoryTest {

	@Test
	public void testCreateFromJSON() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"id\":\"/categories/1\",\"name\":\"chairs\",\"client\":\"/clients/1\",\"products\":[\"/products/1\",\"/products/2\",\"/products/3\",\"/products/4\",\"/products/5\"]}";
		ObjectMapper mapper = new ObjectMapper();
		Category c = mapper.readValue(json, Category.class);
		assertNotNull(c.getId());
		assertNotNull(c.getClient());
		assertNotNull(c.getName());
		assertNotNull(c.getProducts());
		
	}
	
	@Test
	public void testCreateFromJSONAdditionalAttributes() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"id\":\"/categories/1\",\"a\":\"xxxx\",\"name\":\"chairs\",\"client\":\"/clients/1\",\"products\":[\"/products/1\",\"/products/2\",\"/products/3\",\"/products/4\",\"/products/5\"]}";
		ObjectMapper mapper = new ObjectMapper();
		Category c = mapper.readValue(json, Category.class);
		assertNotNull(c.getId());
		assertNotNull(c.getClient());
		assertNotNull(c.getName());
		assertNotNull(c.getProducts());
		
	}
	
	@Test(expected=JsonParseException.class)
	public void testCreateFromInvalidJSON() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"id\":\"/categories/1\"\"a:\"chairs\",\"client\":\"/clients/1\",\"products\":[\"/products/1\",\"/products/2\",\"/products/3\",\"/products/4\",\"/products/5\"]}";
		ObjectMapper mapper = new ObjectMapper();
		Category c = mapper.readValue(json, Category.class);
		assertNotNull(c.getId());
		assertNotNull(c.getClient());
		assertNotNull(c.getName());
		assertNotNull(c.getProducts());
		
	}

}
