package _cw_6.marketbuzz.controller;
public class Message {
	
	private String message;
	private String error;
	
	/**
	 * A constructor for specifying message and error information
	 * @param message Specifies message information
	 * @param error Specifies error information
	 */
	public Message(String message, String error) {
		this.message = message;
		this.error = error;
	}
	
	/**
	 * A constructor for specifying message information
	 * @param message Specifies message information
	 */
	public Message(String message) {
		this.message = message;
		this.error = "";
	}
	
	/**
	 * A getter for the message field
	 * @return The message information stored herein
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * A getter for the error field
	 * @return The error information stored herein
	 */
	public String getError() {
		return error;
	}

}