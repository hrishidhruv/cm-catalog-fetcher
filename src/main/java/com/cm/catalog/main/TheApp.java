package com.cm.catalog.main;

import java.util.List;

import com.cm.catalog.common.CMSystemException;
import com.cm.catalog.dto.Product;
import com.cm.catalog.service.ClientCatalogService;
import com.cm.catalog.service.SampleClientCatalogService;

/**
 * Main entry point of the application
 * @author rdhruv
 *
 */
public final class TheApp {
	public static void main(String[] args) {
		if(args.length < 2) {
			printUsage();
			return;
		}

		String clientUrl = args[0];
		String categoryId = args[1];
		ClientCatalogService service = getCatalogService(clientUrl); 
		
		if(service == null) {
			System.out.println("ClientCatalogService not found. Please check your configuration");
			return;
		}
		try {
			List<Product> products = service.getProductsByCategoryId(categoryId);
			listProductDetails(products);
		} catch (CMSystemException cmse) {
			System.out.println(cmse.getDisplayMessage());
		}
	}

	private static void listProductDetails(List<Product> products) {
		if(products != null && products.size() > 0) {
			for(Product p : products) {
				System.out.format("\n%40s : %10s", p.getDisplayName(), p.getPrice());
			}
			System.out.println(""); //Blank row for formatting
		} else {
			System.out.println("No products found");
		}
		
	}

	private static ClientCatalogService getCatalogService(String clientUrl) {
		return new SampleClientCatalogService(clientUrl);
		
	}

	private static void printUsage() {
		System.out.println("Usage: <client url> <category/id>");
		
	}

}
