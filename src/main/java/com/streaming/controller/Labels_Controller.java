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
 * Servlet implementation class Labels_Controller
 */
public class Labels_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Context context;
	Inter dao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Labels_Controller() {
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
		String action=request.getParameter("action");
		if(action.equals("allLabels")){
			request.setAttribute("allLabels", dao.getLatestLabels());
			request.getRequestDispatcher("labels.jsp").forward(request, response);
		}else if(action.equals("details")){
			int id_lab = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("artistFromLabel", dao.getArtistsFromLabel(id_lab));
			request.setAttribute("id",dao.getLabel(id_lab).getId());
			request.setAttribute("name",dao.getLabel(id_lab).getName());
			request.setAttribute("image",dao.getLabel(id_lab).getPicture());
			request.getRequestDispatcher("label_details.jsp").forward(request, response);
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
