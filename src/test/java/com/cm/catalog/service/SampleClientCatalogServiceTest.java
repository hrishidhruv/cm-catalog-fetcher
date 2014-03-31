package com.cm.catalog.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cm.catalog.common.CMSystemException;
import com.cm.catalog.dto.Category;
import com.cm.catalog.dto.Product;

public class SampleClientCatalogServiceTest {


	private final ClientCatalogService clientCatalogService = new SampleClientCatalogService("http://www.myclient.com/api/v1");
	
	@Test
	public void testGetProductsByCategoryId_ValidCatId() {
		
		List<Product> products = clientCatalogService.getProductsByCategoryId("/categories/1");
		assertTrue(products.size() == 5);
	}

	@Test(expected = CMSystemException.class)
	public void testGetProductsByCategoryId_InvalidCatId() {
		List<Product> products = clientCatalogService.getProductsByCategoryId("/categories/2");
	}
	
	@Test
	public void testGetProductsByCategory() {
		
		Category cat = new Category();
		cat.setProductIds(new ArrayList<String>());
		cat.getProducts().add("/products/1");
		cat.getProducts().add("/products/11");
		cat.getProducts().add("/products/31");
		cat.getProducts().add("/products/41");
		cat.getProducts().add("/products/5");
		cat.getProducts().add("/products/6"); //This has an invalid JSON format
		cat.getProducts().add("/products/7");
		
		List<Product> products = ((SampleClientCatalogService)clientCatalogService).getProductsForCategory(cat);
		assertTrue(products.size() == 3);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testGetCategories() {
		List<Category> categories = clientCatalogService.getCategories();
	}
	
}
