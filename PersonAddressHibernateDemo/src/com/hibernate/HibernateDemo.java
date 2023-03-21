package com.hibernate;

import org.hibernate.Session;

import com.hibernate.domain.Message;
import com.hibernate.utils.HibernateUtil;

public class HibernateDemo {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		Message message = new Message("HelloWolrd from Hibernate-Annotations");
		
		session.save(message);
		session.getTransaction().commit();
		session.close();
	}
}
