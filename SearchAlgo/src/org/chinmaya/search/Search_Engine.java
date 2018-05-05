package org.chinmaya.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Search_Engine extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String name = request.getParameter("search");
		System.out.println(name);
		try {
			search(name,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		@SuppressWarnings("static-access")
		public void search(String text, HttpServletResponse response) throws Exception {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			WordSearch ws = new WordSearch("C:/Study_Mac/Advanced_Computing/W3C_Web_Pages1/Text/");
			int x = ws.Implement_Trie(text);
			out.println("</p>");
			out.println("Total Occurrences: " + x);
			out.println("</p>");
			out.println(ws.search(text, 10));	
	}
}
