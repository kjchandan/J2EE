package com.J2EE.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class drive extends HttpServlet {
	
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
			con = DriverManager.getConnection(url, un, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		
		
		try {
			
			String query2 ="select * from drives";
			Statement stmt = con.createStatement();
			ResultSet res2 = stmt.executeQuery(query2);
			
			writer.println("<h3>Drive conducted at Tapacademy : </h3>");
			
			writer.println("<table border=1>\n"
					+ "			<tr>\n"
					+ "				<th>Id</th>\n"
					+ "				<th>Name</th>\n"
					+ "				<th>10th</th>\n"
					+ "				<th>12th</th>\n"
					+ "				<th>Grade</th>\n"
					+ "				<th>Profile</th>\n"
					+ "				<th>Package</th>\n"
					+ "				<th>Skills</th>\n"
					+ "			</tr>");
			
			while(res2.next()== true) {
				int id =res2.getInt(1);
				String name  = res2.getString(2);
				int ten = res2.getInt(3);
				int twe = res2.getInt(4);
				int grad = res2.getInt(5);
				String profile = res2.getString(6);
				float pac = res2.getFloat(7);
				String skills = res2.getString(8);
				
			writer.println("<tr>\n"
					+ "			<td>\" + id + \"</td>\n"
					+ "			<td>\" + name + \"</td>\n"
					+ "			<td>\" + ten  + \"</td>\n"
					+ "			<td>\" + twe  + \"</td>\n"
					+ "			<td>\" + grad  + \"</td>\n"
					+ "			<td>\" + profile  + \"</td>\n"
					+ "			<td>\" + pac  + \"</td>\n"
					+ "			<td>\" + skills  + \"</td>\n"
					+ "		</tr>");
		}
		writer.println("</table>");
			
			
			
		req.getRequestDispatcher("/eligble").include(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
