package com.cm.catalog.service;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cm.catalog.common.CMSystemException;
import com.cm.catalog.common.ServiceErrorCode;

/**
 * Abstract class the performs the
 * rest calls 
 * @author Hrishi Dhruv
 *
 */
public abstract class AbstractRestServiceClient {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractRestServiceClient.class);
	
	private final String webAPIBaseUrl;
	
	public AbstractRestServiceClient(final String baseUrl) {
		this.webAPIBaseUrl = baseUrl;
	}
	
	/**
	 * TODO:
	 * This is a dummy get that looks up the cahe and not the
	 * actual web service.
	 * I hate to do this... but will try to add the
	 * RestClient implementation if time permits.
	 * @param path
	 * @return
	 */
	public String get(String path) {
		String url = webAPIBaseUrl + path;
		if(contentCache.containsKey(url)) {
			return contentCache.getProperty(url);
		} else {
			throw new CMSystemException(ServiceErrorCode.RESOURCE_NOT_FOUND);
		}
	}
	
	/**
	 * TODO: remove this once integrated with actual REST service.
	 * This is a map of static content 
	 * and is only for demo purpose till its
	 * hooked up with the actual REST service
	 */
	
	private static final Properties contentCache = new Properties();
	
	static {
		loadContentCache();
	}

	private static void loadContentCache() {
		final InputStream stream = AbstractRestServiceClient.class.getResourceAsStream("/data/content-cache.properties");

        try {
            if (stream != null) {
            	contentCache.load(stream);
            } else {
                LOGGER.error("Failed to load content-cache.properties." +
                        " This will result in failure getting data,");
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load content-cache.properties." +
                    " This will result in failure getting data.", e);
        } finally {
            try {
            	if(stream != null) {
            		stream.close();
            	}
			} catch (IOException e) {
				//Ignore. Should probably use IOUtils..closeQuietly
				LOGGER.warn("Failed to close the stream"); 
			}
        }
	}
}
