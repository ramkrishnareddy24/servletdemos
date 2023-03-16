package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.parser.Cookie;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
  
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("username = "+username);
		if(username == null ) {
			out.println("Username not found in session");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		else
		{
			out.println("Username found in session - "+username);
			String favcolor=null;
			for(javax.servlet.http.Cookie cookie : request.getCookies()) {
				if(cookie.getName().equalsIgnoreCase("favouriteColor"))
					favcolor = cookie.getValue();
			}
			out.println("<br><p>User's favourite color = "+favcolor);
		}
	}

}
