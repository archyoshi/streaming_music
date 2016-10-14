package com.streaming.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.streaming.dao.Inter;

/**
 * Servlet implementation class Styles_actions
 */
public class Styles_actions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Context context;
	Inter dao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Styles_actions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			context = new InitialContext();
			dao = (Inter) context.lookup("Stream");
			String action = request.getParameter("action");
			if(action.equals("all")){
				request.setAttribute("AllStyles", dao.getAllStyles());
				request.getRequestDispatcher("styles.jsp").forward(request, response);
				System.out.println("coucou");
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
