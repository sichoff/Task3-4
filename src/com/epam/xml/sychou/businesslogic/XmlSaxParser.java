package com.epam.xml.sychou.businesslogic;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.epam.xml.sychou.exception.logicexception.IncorrectArgumentException;
import com.epam.xml.sychou.model.Product;
import com.epam.xml.sychou.model.TagValue;

public class XmlSaxParser extends XmlParser {
	private static final Logger logger = Logger.getLogger(XmlSaxParser.class);
	private StringBuilder accumulator = new StringBuilder();
	private StringBuilder currentCategory = new StringBuilder();
	private StringBuilder currentSubcategory = new StringBuilder();
	private static final String TAG_NAME_ATTRIBUTE = "name";

	// --------------------------SAX speciphics
	// methods--------------------------------

	public void startDocument() {
		logger.info("Start document");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		logger.info("Start element " + qName);
		if (TagValue.product.name().equals(qName)) {
			singleBean = new Product();
		}
		if (TagValue.category.name().equals(qName)) {
			currentCategory.append(attributes.getValue(TAG_NAME_ATTRIBUTE));
		}
		if (TagValue.subcategory.name().equals(qName)) {
			currentSubcategory.append(attributes.getValue(TAG_NAME_ATTRIBUTE));
		}
		accumulator.setLength(0);
	}

	@Override
	public void characters(char[] c, int start, int length) throws SAXException {
		accumulator.append(c, start, length);
		logger.info("CHARS " + accumulator);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		try {
			switchParam(qName);
			logger.info("End element " + qName);
			accumulator.setLength(0);
		} catch (IncorrectArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void endDocument() {
		accumulator.setLength(0);
		logger.info("End document");
	}

	// --------------------------------------------Additional--------------------------------------
	private void switchParam(String value) throws IncorrectArgumentException {
		if (null == value) {
			throw new IncorrectArgumentException();
		}
		TagValue tag;
		logger.info("tag name: " + value);
		tag = TagValue.valueOf(value.trim());

		switch (tag) {
		case products:
			break;
		case product:
			singleBean.setCategory(currentCategory.toString());
			singleBean.setSubcategory(currentSubcategory.toString());
			addToListOfProduct(singleBean);
			break;
		case category:
			currentCategory.setLength(0);
			break;
		case subcategory:
			currentSubcategory.setLength(0);
			break;
		case productName:
			singleBean.setProductName(accumulator.toString().trim());
			break;
		case provider:
			singleBean.setProvider(accumulator.toString().trim());
			break;
		case model:
			singleBean.setModel(accumulator.toString().trim());
			break;
		case dateOfIssue:
			singleBean.setDateOfIssue(accumulator.toString().trim());
			break;
		case color:
			singleBean.setColor(accumulator.toString().trim());
			break;
		case price:
			singleBean.setPrice(Double.parseDouble(accumulator.toString()
					.trim()));
			break;
		case notInStock:
			singleBean.setNotInStock(Boolean.parseBoolean(accumulator
					.toString().trim()));
			break;
		default:
			break;
		}
	}

	@Override
	public void parse(String docName) throws IncorrectArgumentException {
		logger.info("sax parser starting...");
	}
}
