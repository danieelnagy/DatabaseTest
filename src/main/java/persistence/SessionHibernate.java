package persistence;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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
	
    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
