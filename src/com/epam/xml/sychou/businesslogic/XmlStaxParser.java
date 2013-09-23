package com.epam.xml.sychou.businesslogic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Logger;

import com.epam.xml.sychou.exception.logicexception.IncorrectArgumentException;
import com.epam.xml.sychou.model.Product;
import com.epam.xml.sychou.model.TagValue;

public class XmlStaxParser extends XmlParser {
	private static final Logger logger = Logger.getLogger(XmlStaxParser.class);
	private Product singleBean;
	private static final String TAG_NAME_ATTRIBUTE = "name";
	private StringBuilder currentCategory = new StringBuilder();
	private StringBuilder currentSubcategory = new StringBuilder();
	private StringBuffer accumulator = new StringBuffer();

	public XmlStaxParser() {
	}

	public XmlStaxParser(String doc) throws IncorrectArgumentException {
		setDocName(doc);
	}

	// -------------------------------------------------------------------------------------
	public void parse(String docName) throws IncorrectArgumentException {
		try {
			if (null != docName) {
				setDocName(docName);
			}
			XMLInputFactory factory = XMLInputFactory.newInstance();
			FileInputStream in = new FileInputStream(docName);
			XMLEventReader reader = factory.createXMLEventReader(in);
			while (reader.hasNext()) {
				XMLEvent xmlEvent = reader.nextEvent();
				switch (xmlEvent.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					startDocument();
					logger.info("Start document");
					break;
				case XMLStreamConstants.START_ELEMENT:
					startElement(xmlEvent.asStartElement());
					logger.info("Start element "
							+ xmlEvent.asStartElement().getName());

					break;
				case XMLStreamConstants.CHARACTERS:
					characters(xmlEvent.asCharacters());
					logger.info("Characters "
							+ xmlEvent.asCharacters().getData());
					break;
				case XMLStreamConstants.END_ELEMENT:
					endElement(xmlEvent.asEndElement());
					logger.info("End element "
							+ xmlEvent.asEndElement().getName());

					logger.info("parse continue...");
					break;
				case XMLStreamConstants.END_DOCUMENT:
					endDocument();
					logger.info("End document");
					break;
				default:
					logger.info("default " + xmlEvent.getEventType());
					break;
				}
			}
			logger.info("reader don't have next");
		} catch (FileNotFoundException | XMLStreamException e) {
			throw new IncorrectArgumentException();
		}
	}

	public void parse() throws IncorrectArgumentException {
		parse(null);
	}

	// -------------------------------------------StAX speciphics
	// methods--------------------------------
	public void startDocument() {
		try {
			setListOfProduct(new ArrayList<Product>());
		} catch (IncorrectArgumentException e) {
			e.printStackTrace();
		}
	}

	public void startElement(StartElement element) {
		accumulator.setLength(0);
		if (TagValue.product.name().equals(element.getName().toString())) {
			singleBean = new Product();
		}
		if (TagValue.category.name().equals(element.getName().toString())) {
			currentCategory.append(element.getAttributeByName(new QName(
					TAG_NAME_ATTRIBUTE)));
		}
		if (TagValue.subcategory.name().equals(element.getName().toString())) {
			currentSubcategory.append(element.getAttributeByName(new QName(
					TAG_NAME_ATTRIBUTE)));
		}
	}

	public void characters(Characters charact) {
		accumulator.append(charact);
	}

	public void endElement(EndElement element)
			throws IncorrectArgumentException {
		switchParam(element.getName().toString().trim());
	}

	public void endDocument() {
		accumulator.setLength(0);
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
			logger.info("default switch param");
			break;
		}
	}
}
