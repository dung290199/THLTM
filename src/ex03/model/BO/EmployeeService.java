package ex03.model.BO;

import ex03.model.DAO.EmployeeDAO;
import ex03.model.bean.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public List<Employee> findAll() throws SQLException, ClassNotFoundException {
        List<Employee> result = new ArrayList<>();
        ResultSet resultSet = this.employeeDAO.getAll();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int idpb = resultSet.getInt(3);
            String address = resultSet.getString(4);
            result.add(new Employee(id, name, idpb, address));
        }
        return result;
    }

    public boolean create (Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.create(employee);
    }

    public Employee findById(int id) throws SQLException, ClassNotFoundException {
        return this.employeeDAO.findById(id);
    }

    public List<Employee> findByName(String name) throws SQLException, ClassNotFoundException {
        return this.employeeDAO.findByName(name);
    }

    public boolean update(int id, Employee employee) throws SQLException, ClassNotFoundException {
        return this.employeeDAO.update(id, employee);
    }

    public boolean remove(int id) throws SQLException, ClassNotFoundException {
        return this.employeeDAO.remove(id);
    }
}
