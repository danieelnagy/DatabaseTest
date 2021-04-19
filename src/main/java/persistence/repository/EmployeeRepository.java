package persistence.repository;

import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class EmployeeRepository {

    private SessionFactory sessionFactory;

    public EmployeeRepository() {

        setup();
    }

    public void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .loadProperties("hibernate.cfg.xml")
                .build();
        sessionFactory = new Configuration().buildSessionFactory(registry);
    }

    public void shutdown() {

        sessionFactory.close();
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public void create(Employee employee) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        employee.setName(employee.getName());
        employee.setAdress(employee.getAdress());
        employee.setOfficeId(employee.getOfficeId());
        session.save(employee);
        session.getTransaction().commit();
        session.close();
    }

    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Employee> employees = loadAllData(Employee.class, session);
        session.getTransaction().commit();
        session.close();
        return employees;

    }

    public Employee find(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        session.close();
        return employee;
    }

    public void update(Employee employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(employee);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Employee employee) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(employee);

        session.getTransaction().commit();
        session.close();
    }
}
