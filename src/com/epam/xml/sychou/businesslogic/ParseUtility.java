package com.epam.xml.sychou.businesslogic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.epam.xml.sychou.exception.logicexception.IncorrectArgumentException;
import com.epam.xml.sychou.exception.logicexception.NotValidDocumentException;

import sun.security.krb5.internal.PAEncTSEnc;

import com.epam.xml.sychou.model.*;
import com.epam.xml.sychou.resource.Resource;

public class ParseUtility {
	private static final Logger logger = Logger.getLogger(ParseUtility.class);
	private XmlParser parser;
	private static ParseUtility instance;

	public static synchronized ParseUtility getInstance() {
		if (null == instance) {
			instance = new ParseUtility();
		}
		return instance;
	}

	private ParseUtility() {

	}

	public void parseDocument(String docName, String type)
			throws NotValidDocumentException, IncorrectArgumentException {
		ParserType parserType = ParserType.valueOf(type);
		switch (parserType) {
		case SAX:
			parseXmlDocumentBySax(docName);
			break;
		case StAX:
			parseXmlDocumentByStax(docName);
			break;
		case DOM:
			parseXmlDocumentByDom(docName);
			break;
		default:
			break;
		}

	}

	// ------------------------------------------getters---------------------------------
	public List<Product> getListOfProduct() {
		List<Product> list = new ArrayList<Product>();
		Iterator<Product> iter = parser.getListOfProduct();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list;
	}

	// ---------------------------------------------------

	private void parseXmlDocumentBySax(String docName)
			throws NotValidDocumentException {
		try {
			logger.info(" The parsing proccess was started. ");

			parser = new XmlSaxParser();
			if (null != docName) {
				parser.setDocName(docName);
			}
			SAXParserFactory factory = SAXParserFactory.newInstance();

			factory.setValidating(false);
			SAXParser saxParser = factory.newSAXParser();

			logger.info("current dir: " + System.getProperty("user.dir"));
			InputStream xmlData = new FileInputStream(parser.getDocName());
			saxParser.parse(xmlData, parser);
			parser.setListOfProduct(parser.getListOfProduct());
			logger.info(" The parsing proccess was completed (By SAX). ");
		} catch (SAXException | IncorrectArgumentException
				| ParserConfigurationException | IOException e) {
			throw new NotValidDocumentException();
		}
	}

	public void parseXmlDocumentByDom(String docName)
			throws IncorrectArgumentException {
		logger.info(" The parsing proccess was started. ");
		parser = new XmlDomParser();
		parser.parse(docName);
		parser.setListOfProduct(parser.getListOfProduct());
		logger.info(" The parsing proccess was completed (By DOM). ");

	}

	private void parseXmlDocumentByStax(String docName)
			throws IncorrectArgumentException {
		try {
			logger.info(" The parsing proccess was started. ");
			parser = new XmlStaxParser();
			if (null != docName) {
				parser.setDocName(docName);
			}
			parser.parse(docName);
			parser.setListOfProduct(parser.getListOfProduct());
			logger.info(" The parsing proccess was completed (By StAX). ");
		} catch (NullPointerException e) {

		}
	}

}
