package com.masai.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMUtils {
	static EntityManagerFactory emf;
	static {
		emf=Persistence.createEntityManagerFactory("customer");
	}
	static EntityManager getConnection() {
		return emf.createEntityManager();
	}
}
