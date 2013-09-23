package com.epam.xml.sychou.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * class for reading resource files
 * 
 * @author Uladzimir_Sychou
 * 
 */
public class Resource {
	public static final String resourceFilePath = "com.epam.xml.sychou.resource.Resource";
	public static final String contentFilePath = "com.epam.xml.sychou.resource.Content";

	private static ResourceBundle bundle = ResourceBundle
			.getBundle(contentFilePath);

	public static void setLocale(String locale) {
		bundle = ResourceBundle.getBundle(contentFilePath, new Locale(locale));
	}

	public static String getValue(String key) {
		return bundle.getString(key);
	}

	public static String getValue(String file, String key) {
		return ResourceBundle.getBundle(file).getString(key);
	}

}
