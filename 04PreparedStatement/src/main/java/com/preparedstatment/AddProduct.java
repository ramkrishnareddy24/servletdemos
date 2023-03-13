package com.preparedstatment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/addproduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedstmt;
  
    public AddProduct() {
        super();
    }

    public void init(ServletConfig config) {
		try {
			ServletContext context = config.getServletContext();
			String dburl = context.getInitParameter("dburl");
			String dbuser = context.getInitParameter("dbuser");
			String dbpassword = context.getInitParameter("dbpassword");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dburl,dbuser,dbpassword);
			preparedstmt=connection.prepareStatement("insert into product values(?,?,?,?)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		
		response.setContentType("tex/html");
		PrintWriter out = response.getWriter();
		
		if(!isValidInput(id,true) || !isValidInput(name,false) || !isValidInput(description,false)|| !isValidInput(price,true)) {
			out.println("<h3>Please Enter valid input...</h3>");
			return;
		}
			
		
		try{
			preparedstmt.setInt(1, Integer.parseInt(id));
			preparedstmt.setString(2, name);
			preparedstmt.setString(3, description);
			preparedstmt.setInt(4, Integer.parseInt(price));
		}
		catch(SQLException e) {
			out.println("Product not created error Occurred :"+e.getMessage());
		}


		
	}
	
	private boolean isValidInput(String inputValue,boolean isNumber) {
		if(inputValue == null || inputValue.isBlank() || inputValue.isEmpty()) {
			return false;
		}
		else if(isNumber) {
			try {
				Integer.parseInt(inputValue);
				return true;
			}
			catch(NumberFormatException e) {
				return false;
			}
		}
			else
			{
				return true;
			}
	}
	
	
	
	public void destroy() {
		try {
			if(connection != null) {
				connection.close();
			}
			if(preparedstmt!=null) {
				preparedstmt.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
