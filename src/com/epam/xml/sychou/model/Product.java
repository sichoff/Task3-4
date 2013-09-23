package com.epam.xml.sychou.model;

import org.apache.log4j.Logger;

import com.epam.xml.sychou.exception.logicexception.IncorrectArgumentException;

public class Product {
	private String category;
	private String subcategory;
	private String productName;
	private String provider;
	private String model;
	private String dateOfIssue;
	private String color;
	private double price;
	private boolean notInStock;

	public Product() {

	}

	public void setCategory(String param) throws IncorrectArgumentException {
		if (null == param) {
			throw new IncorrectArgumentException();
		}
		category = param;
	}

	public void setSubcategory(String param) throws IncorrectArgumentException {
		if (null == param) {
			throw new IncorrectArgumentException();
		}
		subcategory = param;
	}

	public void setProductName(String param) throws IncorrectArgumentException {
		if (null == param) {
			throw new IncorrectArgumentException();
		}
		productName = param;
	}

	public void setProvider(String param) throws IncorrectArgumentException {
		if (null == param) {
			throw new IncorrectArgumentException();
		}
		provider = param;
	}

	public void setModel(String param) throws IncorrectArgumentException {
		if (null == param) {
			throw new IncorrectArgumentException();
		}
		model = param;
	}

	public void setDateOfIssue(String param) throws IncorrectArgumentException {
		if (null == param) {
			throw new IncorrectArgumentException();
		}
		dateOfIssue = param;
	}

	public void setColor(String param) throws IncorrectArgumentException {
		if (null == param) {
			throw new IncorrectArgumentException();
		}
		color = param;
	}

	public void setPrice(double param) throws IncorrectArgumentException {
		if (param <= 0) {
			throw new IncorrectArgumentException();
		}
		price = param;
	}

	public void setNotInStock(boolean param) {
		notInStock = param;
	}

	public String getCategory() {
		return category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public String getProduceName() {
		return productName;
	}

	public String getProvider() {
		return provider;
	}

	public String getModel() {
		return model;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public String getColor() {
		return color;
	}

	public double getPrice() {
		return price;
	}

	public boolean isNotInStock() {
		return notInStock;
	}

	// ------------------------------------------------------------------------------
	public String toString() {
		String tmp = getClass().getSimpleName() + " @ Category " + category
				+ ", Subcategory " + subcategory + " Name " + productName
				+ ", Provider " + provider + ", Model " + model
				+ ", Date of Issue " + dateOfIssue + ", Color " + color + ", ";
		return (notInStock ? tmp + "not in stock" : tmp + "price " + price);
	}

	public int hashCode() {
		return (int) (((null == category) ? 0 : category.hashCode())
				+ ((null == subcategory) ? 0 : subcategory.hashCode())
				+ ((null == productName) ? 0 : productName.hashCode())
				+ ((null == provider) ? 0 : provider.hashCode())
				+ ((null == model) ? 0 : model.hashCode())
				+ ((null == dateOfIssue) ? 0 : dateOfIssue.hashCode())
				+ ((null == color) ? 0 : color.hashCode()) + price + (notInStock ? 1
					: 0));
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return false;
		}
		if (null == obj) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product product = (Product) obj;

		if (null == product.category) {
			if (null != this.category) {
				return false;
			}
		}
		if (!this.category.equals(product.category)) {
			return false;
		}

		if (null == product.subcategory) {
			if (null != this.subcategory) {
				return false;
			}
		}
		if (!this.subcategory.equals(product.subcategory)) {
			return false;
		}

		if (null == product.productName) {
			if (null != this.productName) {
				return false;
			}
		}
		if (!this.productName.equals(product.productName)) {
			return false;
		}

		if (null == product.provider) {
			if (null != this.provider) {
				return false;
			}
		}
		if (!this.provider.equals(product.provider)) {
			return false;
		}

		if (null == product.model) {
			if (null != this.model) {
				return false;
			}
		}
		if (!this.model.equals(product.model)) {
			return false;
		}

		if (null == product.dateOfIssue) {
			if (null != this.dateOfIssue) {
				return false;
			}
		}
		if (!this.dateOfIssue.equals(product.dateOfIssue)) {
			return false;
		}

		if (null == product.color) {
			if (null != this.color) {
				return false;
			}
		}
		if (!this.color.equals(product.color)) {
			return false;
		}

		if (this.price != (product.price)) {
			return false;
		}
		if (this.notInStock != (product.notInStock)) {
			return false;
		}
		return true;
	}

	public Product clone() {
		Product cloneObject = new Product();

		cloneObject.category = this.category;
		cloneObject.subcategory = this.subcategory;
		cloneObject.productName = this.productName;
		cloneObject.provider = this.provider;
		cloneObject.model = this.model;
		cloneObject.dateOfIssue = this.dateOfIssue;
		cloneObject.color = this.color;
		cloneObject.price = this.price;
		cloneObject.notInStock = this.notInStock;

		return cloneObject;
	}
}
