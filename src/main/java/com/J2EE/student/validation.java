package com.J2EE.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;


public class validation extends HttpServlet {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet res = null;
	String url =null;
	String un = null;
	String pwd = null;
    
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		
		ServletContext sCon = sc.getServletContext();
		
		url =  sCon.getInitParameter("url");
		un  =  sCon.getInitParameter("username");
		pwd =  sCon.getInitParameter("passowrd");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,un,pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			
			String query ="select * from tablename where un = ? and pwd =?";
			pstmt = con.prepareStatement(query);
			pstmt = setString(1,username);
			pstmt = setString(2,password);
			res = pstmt.executeQuery();
			
			if(res.next()== true) {
				
				writer.println("<h3>Welcome to Tap academy</h3>");
				
				req.getRequestDispatcher("/drive").include(req, resp);
				
			} else {
				req.getRequestDispatcher("/invalidLogin.html").forward(req, resp);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private PreparedStatement setString(int i, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
