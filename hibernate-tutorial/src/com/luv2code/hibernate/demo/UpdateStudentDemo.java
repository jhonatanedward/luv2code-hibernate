package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			// now get a new session and start transaction 
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary
			System.out.println("\nGetting student with id:" + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Scooby");
			
			// commi the transaction
			session.getTransaction().commit();
			
			// NEW CODE
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("Update email for all students ends with '@luv2code.com");
			
			session.createQuery("update Student set email ='luv2code@luv2code.com' where email like '%@luv2code.com'")
			.executeUpdate();
			
			// commi the transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}
		
	}
}





