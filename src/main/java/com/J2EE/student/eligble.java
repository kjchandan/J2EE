package com.J2EE.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class eligble extends HttpServlet {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet res = null;
	String url =null;
	String un = null;
	String pwd = null;
	
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
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		 PrintWriter writer =  resp.getWriter();
		
		try {
			String query1 = "select * from tapstudent where un =? and pwd =?";
		    PreparedStatement pstmt1 = con.prepareStatement(query1);
		    pstmt1.setString(1, username);
		    pstmt1.setString(2, password);
		    ResultSet res1 =  pstmt1.executeQuery();
		    
		    res1.next();
		    
		    int ten = res1.getInt(3);
		    int twe = res1.getInt(4);
		    int grad = res1.getInt(5);
		    
		    String query2 = "select * from drives where 10th <= ? and 12th <= ? and grad <= ?";
		    PreparedStatement pstmt2 = con.prepareStatement(query2);
		    pstmt2.setInt(1,ten);
		    pstmt2.setInt(2,twe);
		    pstmt2.setInt(3,grad);
		    ResultSet res2 = pstmt2.executeQuery();
		    
		    writer.print("<h3>" + res1.getString(2)+" " + "drives you are eligible for:</h3>");
		    
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
				int ten1 = res2.getInt(3);
				int twe1 = res2.getInt(4);
				int grad1 = res2.getInt(5);
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
	   
		    
		    
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
		
		
	}
}

























