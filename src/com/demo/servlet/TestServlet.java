package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String num1 = request.getParameter("firstNum");
		String num2 = request.getParameter("secondNum");
		System.out.println("Received Input : Num1 = "+num1+", Num2 = "+num2);
		int sum;
		try{
			sum = Integer.parseInt(num1.trim())+Integer.parseInt(num2.trim());
			out.print(sum);
		}catch(Exception e){
			out.print(e.getMessage());
			System.out.println("Exception: "+e.getMessage());
		}
		
		//out.println(sum);
//		out.println("<html>");
//		out.println("<body>");
//		out.println("<h1>GET of TestServlet : "+sum+"</h1>");
//		out.println("</body>");
//		out.println("</html>");	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String num1 = request.getParameter("firstNum");
		String num2 = request.getParameter("secondNum");
		System.out.println("Received Input : Num1 = "+num1+", Num2 = "+num2);
		int sum;
		PrintWriter out = response.getWriter();
		try{
			sum = Integer.parseInt(num1.trim())+Integer.parseInt(num2.trim());
			out.print(sum);
		}catch(Exception e){
			e.printStackTrace();
			out.println(e.getMessage());			
		}
	}
}
