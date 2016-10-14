package com.streaming.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.streaming.dao.Inter;
import com.streaming.model.Playlist;
import com.streaming.model.User;

/**
 * Servlet implementation class Playlist_Controller
 */
public class Playlist_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Context context;
	Inter dao = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Playlist_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	try {
		context = new InitialContext();
		dao = (Inter) context.lookup("Stream");
		String action = request.getParameter("action");
		if(action.equals("afficher")){
		int id_playlist= Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession(true);
		session.setAttribute("PlayList_Tracks",dao.getTracksOfPlaylist(id_playlist));
		request.getRequestDispatcher("playlist_details.jsp").forward(request, response);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			context = new InitialContext();
			dao = (Inter) context.lookup("Stream");
			String action = request.getParameter("action");
			Map<String, Object> map = new HashMap<String, Object>();
			boolean isValid = false;
			if (action.equals("creer_playlist")) {

				String name = request.getParameter("nameP");
				int id = dao.getUserByMail(request.getParameter("mail"))
						.getId();
				if (dao.playAlreadyExists(name, id)) {
					isValid = true;
					map.put("message", "Ce nom exist déjà !");
					map.put("isValid", isValid);
					write(response, map);
				} else {
					Playlist p = new Playlist();
					p.setPlaylistname(name);
					p.setUser(dao.getUserByMail(request.getParameter("mail")));
					dao.createPlaylist(p);
					HttpSession session = request.getSession(true);
					session.setAttribute("PlayLists",
							dao.getCurrentPlaylists(p.getUser().getId()));
					isValid = true;
					map.put("message", "Playlist created !");
					map.put("isValid", isValid);
					write(response, map);
				}

			} else if (action.equals("addToPlaylist")) {

				int idplaylist = Integer.parseInt(request
						.getParameter("idplaylist"));
				int idtrack = Integer.parseInt(request.getParameter("idtrack"));
				isValid = true;
				if (dao.TrackInPlaylistAlreadyExists(idplaylist, idtrack)) {
					map.put("message",
							"This Track is already exist in this playlist !");
					map.put("isValid", isValid);
					write(response, map);
				} else {
					dao.addTrackToPlaylist(idplaylist, idtrack);

					HttpSession session = request.getSession(true);
					User u = dao.getUserByMail(session.getAttribute("mail")
							.toString());
					session.setAttribute("PlayLists",
							dao.getCurrentPlaylists(u.getId()));
					map.put("message", "Success ! ");
					map.put("isValid", isValid);
					write(response, map);

				}
			} else if (action.equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				dao.deletePlaylist(id);
				HttpSession session = request.getSession(true);
				User u = dao.getUserByMail(session.getAttribute("mail")
						.toString());
				session.setAttribute("PlayLists",
						dao.getCurrentPlaylists(u.getId()));
				isValid = true;
				map.put("message", "Playlist Deleted");
				map.put("isValid", isValid);
				write(response, map);

			} else if (action.equals("deleteAlbum")) {
				int id = Integer.parseInt(request.getParameter("id"));
				dao.removeReleaseToFavorities(id);
				HttpSession session = request.getSession(true);
				User u = dao.getUserByMail(session.getAttribute("mail")
						.toString());
				session.setAttribute("Fav", dao.getFavorities(u.getId()));
				isValid = true;
				map.put("message", "Album deleted from favorites");
				map.put("isValid", isValid);
				write(response, map);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void write(HttpServletResponse response, Map<String, Object> map)
			throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(new Gson().toJson(map));

	}

}
