package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save Java Object

			// create 3 student objects
			System.out.println("Create 3 student objects...");
			Student oneStudent = new Student("John", "Doe", "john@luv2code.com");
			Student twoStudent = new Student("Mary", "Public", "mary@luv2code.com");
			Student threStudent = new Student("Bonita", "Applebum", "bonita@luv2code.com");
			
			// start transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving student...");
			session.save(oneStudent);
			session.save(twoStudent);
			session.save(threStudent);

			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
				}
		
	}
}
