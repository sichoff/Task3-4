package com.epam.xml.sychou.exception.logicexception;

import org.apache.log4j.Logger;

/**
 * exception, which will be threw if there is a logic error
 * 
 * @author Uladzimir_Sychou
 * 
 */
public class IncorrectArgumentException extends ModelException {
	private final static Logger logger = Logger
			.getLogger(IncorrectArgumentException.class);
	private Exception hiddenException;
	private String message;

	public String getMessage() {
		return message;
	}

	public IncorrectArgumentException(String text) {
		message = text;
		logger.error(text);
	}

	public IncorrectArgumentException() {
	}

	public Exception getHiddenException() {
		return hiddenException;
	}
}
