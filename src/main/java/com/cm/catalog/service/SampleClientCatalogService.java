package com.cm.catalog.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cm.catalog.common.CMSystemException;
import com.cm.catalog.common.ServiceErrorCode;
import com.cm.catalog.dto.Category;
import com.cm.catalog.dto.Product;

/**
 * Implementation of the Sample client catalog service
 * @author rdhruv
 *
 */
public class SampleClientCatalogService 
	extends AbstractRestServiceClient 
	implements ClientCatalogService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SampleClientCatalogService.class);
	
	public SampleClientCatalogService(String baseUrl) {
		super(baseUrl);
	}
	/**
	 * Method to get all the categories.
	 * This method is not implemented as 
	 * it is not required so far.
	 */
	public List<Category> getCategories() {
		throw new UnsupportedOperationException();
	}

	/**
	 * The list of products from categoryId
	 * The category Id is assumed to be resource path to 
	 * the specific category
	 */
	public List<Product> getProductsByCategoryId(String id) {
		String jsonCategory = null;
		try {
			jsonCategory = get(id);
			//Convert the JSON to category
			ObjectMapper mapper = new ObjectMapper();
			Category cat = mapper.readValue(jsonCategory, Category.class);
			return getProductsForCategory(cat);
			
		} catch (CMSystemException cmse) {
			// If the exception is thrown when fetching product...
			// should what ever is there in the products list be returned?
			// for now throwing exception
			LOGGER.info("Failed to get Product by category Id {}", id);
			LOGGER.error("Error: ", cmse);
			throw new CMSystemException(ServiceErrorCode.CATEGORY_NOT_FOUND);
		} catch (IOException  e) {
			LOGGER.info("Failed to get Product by category Id {}", id);
			LOGGER.error("Error: ", e);
			throw new CMSystemException(ServiceErrorCode.UNEXPECTED_EXCEPTION, e);
		}
		
		
	}
	
	/**
	 * Get the list of products from category object
	 * This method is marked internal to make it testable
	 * @param cat
	 * @return
	 */
	List<Product> getProductsForCategory(Category cat) {
		List<Product> products = new ArrayList<Product>();
		if(cat.getProducts().size() > 0) {
			ObjectMapper mapper = new ObjectMapper();
			for(String productId : cat.getProducts()) {
				//Catch exception if product not found so you can continue
				try {
					String productJSON = get(productId);
					
					Product product = mapper.readValue(productJSON, Product.class);
					products.add(product);
					
				} catch (CMSystemException cmse) {
					
					if(cmse.getErrorCode().equals(ServiceErrorCode.RESOURCE_NOT_FOUND)) {
						LOGGER.warn("Product resource not found {} ", productId);
						
						continue;
					} else { //Not sure what the exception so better throw.
						LOGGER.warn("Failed to get Product by Id {}", productId);
						LOGGER.error("Error: ", cmse);
						throw cmse;
					}
				} catch (IOException  e) {
					//Most probably caused due to parsing exception of JSON string...
					//continue with next product id..
					continue;
				}
				
			}
		}
		return products;
	}

}
