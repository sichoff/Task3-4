package com.epam.xml.sychou.businesslogic;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.xml.sychou.exception.logicexception.IncorrectArgumentException;
import com.epam.xml.sychou.model.Product;
import com.epam.xml.sychou.model.TagValue;

public class XmlDomParser extends XmlParser {
	private static final Logger logger = Logger.getLogger(XmlDomParser.class);
	private StringBuilder currentCategory = new StringBuilder();
	private StringBuilder currentSubcategory = new StringBuilder();
	private Document document;

	public XmlDomParser() {
	}

	public XmlDomParser(String docName) throws IncorrectArgumentException {
		setDocName(docName);
	}

	// --------------------------------------setters---------------------------------
	public void setDocument(Document doc) throws IncorrectArgumentException {
		if (null == doc) {
			throw new IncorrectArgumentException();
		}
		document = doc;
	}

	// --------------------------------getters---------------------------------------------
	public Document getDocument() {
		return document;
	}

	// ------------------------------------------------------------------------------------
	public void parse(String docName) throws IncorrectArgumentException {
		try {
			setDocName(docName);
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			document = docBuilder.parse(docName);
			parseNode(document.getDocumentElement());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new IncorrectArgumentException();
		}
	}

	private void parseNode(Node node) throws IncorrectArgumentException {
		if (node.getNodeName().equals("#text")) {
			logger.info("Element is #text");
			return;
		}
		if (node.getNodeName().equals(TagValue.product.name())) {
			logger.info("Start new edition entity. ");
			singleBean = new Product();
		}
		if (node.getNodeName().equals(TagValue.category.name())) {
			setParameter(node.getNodeName(), getElementAttribute(node, "name"));
		}
		if (node.getNodeName().equals(TagValue.subcategory.name())) {
			setParameter(node.getNodeName(), getElementAttribute(node, "name"));
		}

		if (getElementContent(node) != null
				&& !(getElementContent(node).equals("")))
			setParameter(node.getNodeName(), getElementContent(node));

		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			parseNode(nodeList.item(i));
		}
		if (node.getNodeName().equals(TagValue.product.name())) {
			singleBean.setCategory(currentCategory.toString());
			singleBean.setSubcategory(currentSubcategory.toString());
			addToListOfProduct(singleBean.clone());
			singleBean = null;
		}
	}

	private void setParameter(String parameter, String value)
			throws IncorrectArgumentException {
		if (null == parameter) {
			throw new IncorrectArgumentException();
		}
		TagValue tag;
		logger.info("tag name: " + parameter);
		logger.info("value: " + value);
		tag = TagValue.valueOf(parameter.trim());

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
			currentCategory.append(value);
			break;
		case subcategory:
			currentSubcategory.setLength(0);
			currentSubcategory.append(value);
			break;
		case productName:
			singleBean.setProductName(value);
			break;
		case provider:
			singleBean.setProvider(value);
			break;
		case model:
			singleBean.setModel(value);
			break;
		case dateOfIssue:
			singleBean.setDateOfIssue(value);
			break;
		case color:
			singleBean.setColor(value);
			break;
		case price:
			singleBean.setPrice(Double.parseDouble(value));
			break;
		case notInStock:
			singleBean.setNotInStock(Boolean.parseBoolean(value));
			break;
		default:
			break;
		}
	}

	private String getElementContent(Node node) {
		Node contentNode = node.getFirstChild();
		if (contentNode != null)
			if (contentNode.getNodeName().equals("#text")) {
				String value = contentNode.getNodeValue();
				if (value != null)
					return value.trim();
			}
		return null;
	}

	private String getElementAttribute(Node node, String name) {
		NamedNodeMap attributes = node.getAttributes();
		Node nameAttr = attributes.getNamedItem("name");
		return nameAttr.getNodeValue();
	}
}
