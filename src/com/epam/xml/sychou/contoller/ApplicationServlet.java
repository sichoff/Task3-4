package com.epam.xml.sychou.contoller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.xml.sychou.model.*;
import org.apache.log4j.Logger;

import com.epam.xml.sychou.businesslogic.ParseUtility;
import com.epam.xml.sychou.exception.logicexception.IncorrectArgumentException;
import com.epam.xml.sychou.exception.logicexception.NotValidDocumentException;

/**
 * Servlet implementation class ApplicationServlet
 */
@WebServlet("/ApplicationServlet")
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(ApplicationServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplicationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * ParseUtility util = ParseUtility.getInstance(); try {
		 * util.parseDocument("product.xml",
		 * request.getParameter("parserType")); } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * logger.info("unSort by title:"); Iterator<Product> iter =
		 * util.getListOfProduct().iterator(); while (iter.hasNext()) {
		 * logger.info(iter.next()); }
		 */
		request.setAttribute("test", "ololo");
		RequestDispatcher dispatch = request
				.getRequestDispatcher("success.jsp");
		/* request.setAttribute("list", util.getListOfProduct()); */
		Product pr = new Product();
		try {
			pr.setPrice(100);
		} catch (IncorrectArgumentException e) {
			// XXX Auto-generated catch block
			e.printStackTrace();
		}
		dispatch.forward(request, response);
		// util.transformXml2Html("editions.xml", "xml2html.xsl",
		// "result.html");
		// util.ValidationXmlDoc("editions.xml", "schema.xsd");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParseUtility util = ParseUtility.getInstance();
		try {
			util.parseDocument("product.xml",
					request.getParameter("parserType"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("unSort by title:");
		Iterator<Product> iter = util.getListOfProduct().iterator();
		while (iter.hasNext()) {
			logger.info(iter.next());
		}
	}

}
