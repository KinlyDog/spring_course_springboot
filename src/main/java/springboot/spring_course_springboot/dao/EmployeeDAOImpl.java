package springboot.spring_course_springboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.spring_course_springboot.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Employee> getAllEmployees() {
        return entityManager.createQuery(
                "from Employee", Employee.class).getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {
        Employee newEmp = entityManager.merge(employee);
        employee.setId(newEmp.getId());
    }

    @Override
    public Employee getEmployee(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Query query = entityManager.createQuery
                ("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
