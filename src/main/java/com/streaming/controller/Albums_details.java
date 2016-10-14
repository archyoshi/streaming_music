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

public class Albums_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Context context;
	Inter dao = null; 
   
    public Albums_details() {
        super();
    }

    /*
     * Dans la partie doGet en premier temps je récupère 
     * 
     */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			context = new InitialContext();
			dao = (Inter) context.lookup("Stream");
			String action = request.getParameter("action");
			if(action.equals("detailsAlbum")){
				int id_album = Integer.parseInt(request.getParameter("albumid"));		
				HttpSession session = request.getSession(true);
				String mail = session.getAttribute("mail").toString();
				session.setAttribute("FavAlbumUser", dao.getFavorities(dao.getUserByMail(mail).getId()));
				session.setAttribute("Album_id", id_album);
				session.setAttribute("StyleAlbum", dao.getRelease(id_album).getStyle().getId());
				session.setAttribute("AlbumSel", dao.getTracksOfAlbum(id_album));
				session.setAttribute("getReleaseWithDetails", dao.getReleaseWithDetails(id_album));
				request.getRequestDispatcher("album_details.jsp").forward(request, response);
			}else if(action.equals("getSimilar")){
				int id_style= Integer.parseInt(request.getParameter("styleid"));
				request.setAttribute("Semelar_Albums", dao.getSimilarReleases(id_style));
				request.getRequestDispatcher("semelar_albums.jsp").forward(request, response);
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
