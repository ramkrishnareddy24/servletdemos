package com;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private PreparedStatement preparedstmt;

	public LoginServlet() {
		super();
	}

	public void init(ServletConfig config) {
		try {
			ServletContext context = config.getServletContext();
			String dburl = context.getInitParameter("dburl");
			String dbuser = context.getInitParameter("dbuser");
			String dbpassword = context.getInitParameter("dbpassword");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dburl, dbuser, dbpassword);
			preparedstmt = connection.prepareStatement("select *from user where email=? and password=?");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
	

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (!isValidInput(username, false) || !isValidInput(password, false)) {
			out.println("<h3>Please Enter valid input...</h3>");
			return;
		}

		try {
			preparedstmt.setString(1, username);
			preparedstmt.setString(2, password);
			
			ResultSet resultSet = null;
			
			boolean result = preparedstmt.execute();
			
			if(result)
				resultSet = preparedstmt.getResultSet();
			
			if(resultSet.next()) {
				System.out.println("User Succesfully Logged In");
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				response.addCookie(new Cookie("favouriteColor","Black"));
				response.addCookie(new Cookie("token","!@#$qwe2992"));
				RequestDispatcher rd = request.getRequestDispatcher("home");
				rd.forward(request, response);
			}
			else
			{
				out.println("User NOT found");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);

			}
			
		} catch (SQLException e) {
			out.println("Product not created error Occurred :" + e.getMessage());
		}

	}

	private boolean isValidInput(String inputValue, boolean isNumber) {
		if (inputValue == null || inputValue.isBlank() || inputValue.isEmpty()) {
			return false;
		} else if (isNumber) {
			try {
				Integer.parseInt(inputValue);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return true;
		}
	}

	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedstmt != null) {
				preparedstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
