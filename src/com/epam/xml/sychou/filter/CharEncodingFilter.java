package com.epam.xml.sychou.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter, which setting project default encoding
 * 
 * @author Uladzimir_Sychou
 * 
 */
public class CharEncodingFilter implements Filter {

	private FilterConfig filterConfig = null;

	private String encoding = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = this.filterConfig.getInitParameter("encoding");
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (encoding != null) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}
}