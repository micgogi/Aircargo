package com.brajesh.cargo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

	public JwtFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		final HttpServletRequest request=(HttpServletRequest)req;
		final HttpServletResponse response=(HttpServletResponse)res;
		final String authHeader=request.getHeader("Authorization");
		if("OPTIONS".equals(request.getMethod()))
		{
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		}
		else
		{
			System.out.println(authHeader);
			if(authHeader==null|| !authHeader.startsWith("Bearer")){
				throw new ServletException("missing or invalid exception");
			}
			
			final String token=authHeader.substring(7);
			final Claims claims=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
			request.setAttribute("claims", claims);
			chain.doFilter(request, response);
			
		}
		
		
	}

}
