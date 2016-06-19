package com.link.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DemoFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("----Demo Filter Init----");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Before Demo Filter Execute");
		chain.doFilter(request, response);  //让目标资源执行，放行
		System.out.println("After Demo Filter Execute");
	}

	public void destroy() {
		System.out.println("----Demo Filter Destroy----");
	}

}
