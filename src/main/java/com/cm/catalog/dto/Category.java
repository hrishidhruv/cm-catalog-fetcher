package com.cm.catalog.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Category dto created by converting the 
 * JSON response. 
 * This class does not an overriden equals or hashcode method
 * as it is not required so far. But it is definitely a good 
 * candidates for those.
 * @author Hrishi Dhruv
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

	private String id;
	private String client;
	private String name;
	private List<String> products;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClient() {
		return client;
	}
	public void setClientId(String client) {
		this.client = client;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getProducts() {
		return products;
	}
	public void setProductIds(List<String> products) {
		this.products = products;
	}
}
