package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entities.Customer;

public class SessionHibernate {

	private SessionFactory sessionFactory;

	public void setup() {

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.loadProperties("hibernate.cfg.xml").build();
		sessionFactory = new Configuration().buildSessionFactory(registry);
	}
	
	public void Exit() {
		
		sessionFactory.close();
		
	}
	
	public void create(Customer customer) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(customer);
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	
}
