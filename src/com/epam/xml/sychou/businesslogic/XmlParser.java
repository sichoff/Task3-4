package com.epam.xml.sychou.businesslogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.xml.sychou.exception.logicexception.IncorrectArgumentException;
import com.epam.xml.sychou.model.Product;
import com.epam.xml.sychou.resource.Resource;

public abstract class XmlParser extends DefaultHandler {
	private static final String XML_DOC_PATH = "xml.path";
	private static final Logger logger = Logger.getLogger(XmlParser.class);
	private List<Product> listOfProduct = new ArrayList<Product>();
	protected String docName = Resource.getValue(Resource.resourceFilePath,
			XML_DOC_PATH);
	protected Product singleBean;

	// --------------------------------------setters---------------------------------------
	public void setListOfProduct(List<Product> list)
			throws IncorrectArgumentException {
		if (null == list) {
			throw new IncorrectArgumentException();
		}
		List<Product> newList = new ArrayList<Product>();
		Iterator<Product> iter = list.iterator();
		while (iter.hasNext()) {
			newList.add(iter.next());
		}
	}

	public void setListOfProduct(Iterator<Product> iter)
			throws IncorrectArgumentException {
		if (null == iter) {
			throw new IncorrectArgumentException();
		}
		List<Product> newList = new ArrayList<Product>();
		while (iter.hasNext()) {
			newList.add(iter.next());
		}
		this.listOfProduct = new ArrayList<Product>(newList);
	}

	public boolean addToListOfProduct(Product bean)
			throws IncorrectArgumentException {
		if (null == bean) {
			throw new IncorrectArgumentException();
		}
		return listOfProduct.add(bean);
	}

	public void setSingleBean(Product bean) throws IncorrectArgumentException {
		if (null == bean) {
			throw new IncorrectArgumentException();
		}
		singleBean = bean;
	}

	public void setDocName(String docName) throws IncorrectArgumentException {
		if (null == docName) {
			throw new IncorrectArgumentException();
		}
		this.docName = docName;
	}
	public abstract void parse(String docName) throws IncorrectArgumentException;

	// -------------------------------------getters----------------------------------------
	public Iterator<Product> getListOfProduct() {
		return listOfProduct.iterator();
	}

	public Product getSingleBean() {
		return singleBean;
	}

	public String getDocName() {
		return docName;
	}
}
