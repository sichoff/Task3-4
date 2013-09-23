package com.epam.xml.sychou.exception.logicexception;

import org.apache.log4j.Logger;

public class NotValidDocumentException extends Businesslogicexception {
	private final static Logger logger = Logger
			.getLogger(NotValidDocumentException.class);
	private Exception hiddenException;
	private String message;

	public String getMessage() {
		return message;
	}

	public NotValidDocumentException(String text) {
		message = text;
		logger.error(text);
	}

	public NotValidDocumentException() {
	}

	public Exception getHiddenException() {
		return hiddenException;
	}
}
