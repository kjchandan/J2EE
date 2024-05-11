package com.J2EE.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {


    	@Override
    public void init() throws ServletException {
    	System.out.println("init() method called");
    }
    	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Doget() method called");
		
//		response.setContentType("text/html");
//	 	PrintWriter writer  = response.getWriter();
//	 	writer .println("<h1>Hello world</h1>");
		
		
/*		getParamater()   */
		
//		String name = request.getParameter("name");
//		String design = request.getParameter("design");
//		String ts = request.getParameter("techskills");
//		
//		System.out.println("Name :"+ name);
//		System.out.println("Designation :" + design);
//		System.out.println("TechSkills :" + ts);
		
		
/*		getParamaterNames()=>Enumerator(Collections->Strings) */
		
//		Enumeration<String> pN  = request.getParameterNames();
//		while(pN.hasMoreElements()) {
//			System.out.println(pN.nextElement());
//		}
		
	
/*		getParameterValues()   */
		
		String name = request.getParameter("name");
		String design = request.getParameter("design");
		String[] pV = request.getParameterValues("techskills");
		
		System.out.println("name :"+ name);
		System.out.println("designation :" + design);
		System.out.print("Technical Skills :");
		for(String i:pV) {
			System.out.print(i +" ");
		}
		
/*		Send response to client =>(Static response) */
		
//		RequestDispatcher rd = request.getRequestDispatcher("staticresponse.html");
//		rd.forward(request,response);
		
		
/*		Send response to client =>(Dynamic response) */
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta charset=\"UTF-8\">\n"
				+ "<title>Thank you page</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "	<h3>Thank you "+name+" ,for your response </h3>\n"
				+ "</body>\n"
				+ "</html>");
	 
	}

	

	
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
	
	
	
	
	
	
//	@Override
//	public void destroy() {
//		System.out.println("destory() method called");
//	}

}
