package com.streaming.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.streaming.dao.Inter;

public class Test {

	public static void main(String[] args) {
		Context context;
		Inter dao = null;
		try {
			context = new InitialContext();
			dao = (Inter) context.lookup("Stream");
			System.out.println(dao.authenticate("karim@gmail.com", "hassan"));
			System.out.println("dfsf");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
