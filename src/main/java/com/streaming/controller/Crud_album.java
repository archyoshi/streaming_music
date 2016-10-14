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
import com.streaming.model.User;

class Crud_album extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Context context;
	Inter dao = null;

	public Crud_album() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			context = new InitialContext();
			dao = (Inter) context.lookup("Stream");

			String action = request.getParameter("action");
			Map<String, Object> map = new HashMap<String, Object>();
			boolean isValid = false;
			if (action.equals("AddAlbumFav")) {
				int albumid = Integer.parseInt(request.getParameter("albumid"));
				String mail = request.getParameter("mail");
				int userid = dao.getUserByMail(mail).getId();
				if (dao.AlbumFavoriteAlreadyExists(userid, albumid)) {
					map.put("message",
							"Album already exits in your favorites ! ");
					map.put("isValid", isValid);
					write(response, map);
				} else {
					User user = dao.getUser(userid);
					dao.addReleaseToFavorities(albumid, user);
					HttpSession session = request.getSession(true);
					session.setAttribute("Fav",
							dao.getFavorities(dao.getUserByMail(mail).getId()));
					isValid = true;
					map.put("message", "Success !");
					map.put("isValid", isValid);
					write(response, map);
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void write(HttpServletResponse response, Map<String, Object> map)
			throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(new Gson().toJson(map));

	}

}
