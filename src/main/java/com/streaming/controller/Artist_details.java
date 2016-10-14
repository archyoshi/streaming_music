package com.streaming.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.streaming.dao.Inter;

/**
 * Servlet implementation class Artist_details
 */
public class Artist_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	Context context;
	Inter dao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Artist_details() {
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
			if(action.equals("allArtists")){
				request.setAttribute("allArtists", dao.getLatestArtists());
				request.getRequestDispatcher("artists.jsp").forward(request, response);
			}else if(action.equals("artistDetails")){
		
			int id = Integer.parseInt(request.getParameter("artist"));
			HttpSession session = request.getSession(true);
			session.setAttribute("name", dao.getArtist(id).getName());
			session.setAttribute("years_active", dao.getArtist(id).getYears_active());
			session.setAttribute("description", dao.getArtist(id).getDescription());
			session.setAttribute("ReleasesFromArtist", dao.getReleasesFromArtist(id));
			request.getRequestDispatcher("artist_details.jsp").forward(request, response);
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
