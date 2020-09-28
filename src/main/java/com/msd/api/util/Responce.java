package com.msd.api.util;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Class Responce.
 */
public class Responce implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1765777486507471736L;
	
	/** The success. */
	private Boolean success;
	
	/** The message. */
	private String message;
	
	/** The data. */
	private Object data;
	
	/**
	 * Gets the success.
	 *
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}
	
	/**
	 * Sets the success.
	 *
	 * @param success the new success
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
