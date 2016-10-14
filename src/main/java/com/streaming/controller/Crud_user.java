package com.streaming.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.streaming.dao.Inter;
import com.streaming.model.User;

public class Crud_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Context context;
	Inter dao = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Crud_user() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			
			context = new InitialContext();
			dao = (Inter) context.lookup("Stream");
			String action = request.getParameter("action");
			if (action.equals("crud")) {
				HttpSession session = request.getSession(true);
				User u = dao.getUserByMail(session.getAttribute("mail")
						.toString());
				session.setAttribute("Lastname", u.getNom());
				session.setAttribute("Firstname", u.getPrenom());
				session.setAttribute("Id", u.getId());
				request.getRequestDispatcher("account.jsp").forward(request, response);
			} else if (action.equals("deleteUser")) {
				HttpSession session = request.getSession(true);
				dao.deleteUser(dao.getUserByMail(
						session.getAttribute("mail").toString()).getId());
				session.invalidate();
				response.sendRedirect("index.jsp");
			} else if (action.equals("all")) {
				request.setAttribute("AllUsers", dao.getAllUsers());
				response.sendRedirect("user.jsp");
			} else if (action.equals("deconnexion")) {
				HttpSession session = request.getSession(true);
				session.invalidate();
				response.sendRedirect("index.jsp");
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			if (action.equals("ajouter")) {
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String mail = request.getParameter("mail");
				String password = request.getParameter("password");
				if (dao.emailAlreadyExists(mail)) {
					map.put("message", "Ce mail exist déjà !");
					map.put("isValid", isValid);
					write(response, map);
				} else {
					User u = new User();
					u.setNom(nom);
					u.setPrenom(prenom);
					u.setMot_passe(password);
					u.setMail(mail);
					dao.createUser(u);
					isValid = true;
					map.put("message", "User created with success ! ");
					map.put("isValid", isValid);
					write(response, map);
				}
			} else if (action.equals("authentification")) {
				String mail = request.getParameter("mail");
				String password = request.getParameter("password");
				if (dao.authenticate(mail, password)) {
					HttpSession session = request.getSession(true);
					session.setAttribute("mail", mail);
					session.setAttribute("password", password);
					session.setAttribute("Alltracks", dao.getLatestTrack());
					session.setAttribute("AllAlbums", dao.getLatestReleases());
					session.setAttribute("Fav",
							dao.getFavorities(dao.getUserByMail(mail).getId()));
					session.setAttribute("PlayLists", dao
							.getCurrentPlaylists(dao.getUserByMail(mail)
									.getId()));

					isValid = true;
					map.put("isValid", isValid);
					map.put("url", "home.jsp");
					write(response, map);
				} else {
					map.put("message", "Erreur d'authentification !  ");
					map.put("isValid", isValid);
					write(response, map);
				}
			} else if (action.equals("modify")) {
				HttpSession session = request.getSession(true);
				User u = dao.getUserByMail(session.getAttribute("mail")
						.toString());
				String Firstname = request.getParameter("Firstname");
				String Lastname = request.getParameter("Lastname");
				String mail = request.getParameter("mail");
				String password = request.getParameter("password");
				u.setPrenom(Firstname);
				u.setNom(Lastname);
				u.setMail(mail);
				u.setMot_passe(password);
				dao.updateUser(u);
				isValid = true;
				map.put("message", "User updated with success ! ");
				map.put("isValid", isValid);
				write(response, map);
			} else if (action.equals("invite")) {
				String mail_inv = request.getParameter("sendInv");
				System.out.println(mail_inv);
				dao.inviteUser(mail_inv);
				isValid = true;
				map.put("message", "Invitation Success ! ");
				map.put("isValid", isValid);
				write(response, map);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private void write(HttpServletResponse response, Map<String, Object> map)
			throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(new Gson().toJson(map));

	}

}
