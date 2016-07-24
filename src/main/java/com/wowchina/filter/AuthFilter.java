package com.wowchina.filter;

import com.wowchina.domain.User;
import com.wowchina.service.UserService;

import javax.servlet.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    private UserService userService;

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("----Auth Filter Init----");
        this.userService = new UserService();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Before Auth Filter Execute");
		chain.doFilter(request, response);  //让目标资源执行，放行
		System.out.println("After Auth Filter Execute");
	}

	public void destroy() {
		System.out.println("----Auth Filter Destroy----");
	}

}
