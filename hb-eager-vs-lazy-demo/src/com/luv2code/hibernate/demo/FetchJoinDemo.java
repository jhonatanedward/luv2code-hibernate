package com.luv2code.hibernate.demo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start transaction
			session.beginTransaction();
			
			// option 2: Hibernate query with HQL
			
			// get instructor from db
			int theId = 1;
			
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
							+ "JOIN FETCH i.courses "
							+ "where i.id = :theInstructorId",
							Instructor.class);

			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("luv2code: Instructor: " + tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			// close session
			session.close();
			
			System.out.println("\nluv2code: The session is now close\n");
			
			// since course are lazy loaded.. this should fail
			
			// get course for the instructor
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
			
			System.out.println("Done!");
			
		}finally {
			// add clean up code
			session.close();
			factory.close();
		}
		
	}
}
