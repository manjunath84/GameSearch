/**
 * 
 */
package com.mistplay.game.exceptions;

import java.util.Date;

import org.springframework.web.context.request.WebRequest;

/**
 * @author ms17
 *
 */
public class ExceptionResponse {
	
	private Date Timestamp;
	private String message;
	private String details;

	public ExceptionResponse(Date date, String message, String details) {
		super();
		this.Timestamp = date;
		this.message = message;
		this.details = details;
	}

}
