package com.cm.catalog.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Common System wide exception
 * @author Hrishi Dhruv
 *
 */
public class CMSystemException extends RuntimeException {

	/**
	 * the serial version UID
	 */
	private static final long serialVersionUID = 1301231L;
	private static final Properties messages = new Properties();
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CMSystemException.class);
	static { loadMessages(); }
	private final ErrorCode errorCode;
	private final Throwable parentException;
	
	public CMSystemException(ErrorCode errorCode, Throwable exception) {
		this.errorCode = errorCode;
		this.parentException = exception;
	}
	
	public CMSystemException(ErrorCode errorCode) {
		this(errorCode, null);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	public Throwable getParentException() {
		return this.parentException;
	}

	public String getDisplayMessage() {
		if(messages != null) {
			return messages.getProperty(this.errorCode.toString(), this.errorCode.toString());
		} else {
			return this.errorCode.toString();
		}
	}
	
	private static void loadMessages() {
		final InputStream stream = CMSystemException.class.getResourceAsStream("/error_messages.properties");

        try {
            if (stream != null) {
            	messages.load(stream);
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
