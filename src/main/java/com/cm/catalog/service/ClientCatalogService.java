package com.cm.catalog.service;

import java.util.List;

import com.cm.catalog.dto.Category;
import com.cm.catalog.dto.Product;

/**
 * Interface for Client specific web api to 
 * get the catalog information.
 * @author Hrishi Dhruv
 *
 */
public interface ClientCatalogService {
	public List<Category> getCategories();
	public List<Product> getProductsByCategoryId(String id);
}
