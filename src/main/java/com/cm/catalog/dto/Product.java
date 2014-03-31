package com.cm.catalog.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Product dto created by converting the 
 * JSON response. 
 * This class does not an overriden equals or hashcode method
 * as it is not required so far. But it is definitely a good 
 * candidates for those.
 * 
 * @author Hrishi Dhruv
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	private String id;
	private String internalName;
	private String displayName;
	private BigDecimal price;
	private boolean active;
	private String category;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInternalName() {
		return internalName;
	}
	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
