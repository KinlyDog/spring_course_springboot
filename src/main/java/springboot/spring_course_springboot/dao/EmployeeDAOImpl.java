package springboot.spring_course_springboot.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.spring_course_springboot.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Employee> getAllEmployees() {
        Session session = entityManager.unwrap(Session.class);

        return session.createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);

        return session.get(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery
                ("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
