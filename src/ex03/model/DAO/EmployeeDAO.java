package ex03.model.DAO;

import ex03.model.bean.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private ConnectionMySQL conn = new ConnectionMySQL();;

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `nhanvien` LIMIT 0, 10";
        PreparedStatement statement = this.conn.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public boolean create (Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `nhanvien`(HoTen, IDPB, DiaChi) VALUES (?, ?, ?)";

        PreparedStatement statement = this.conn.getConnection().prepareStatement(sql);
        statement.setString(1, employee.getName());
        statement.setInt(2, employee.getIdpb());
        statement.setString(3, employee.getAddress());

        int row = statement.executeUpdate();
        return (row > 0);
    }

    public Employee findById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `nhanvien` WHERE (`IDNV` = '" + id + "')";

        PreparedStatement statement = this.conn.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        resultSet.next();
        String name = resultSet.getString(2);
        int idpb = resultSet.getInt(3);
        String address = resultSet.getString(4);

        Employee employee = new Employee(id, name, idpb, address);
        return employee;
    }

    public List<Employee> findByName(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `nhanvien` WHERE (`HoTen` LIKE '%" + name + "%')";

        PreparedStatement statement = this.conn.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Employee> result = new ArrayList<>();
        while ( resultSet.next() ){
            int id = resultSet.getInt(1);
            String nameTemp = resultSet.getString(2);
            int idpb = resultSet.getInt(3);
            String address = resultSet.getString(4);
            result.add( new Employee(id, nameTemp, idpb, address) );
        }
        return result;
    }

    public boolean update(int id, Employee employee) throws SQLException, ClassNotFoundException {
        String name = employee.getName();
        int idpb = employee.getIdpb();
        String address = employee.getAddress();

        String sql = "UPDATE `nhanvien` SET `HoTen` = '"+ name
                                            + "', `IDPB` = '" + idpb
                                            + "', `DiaChi` = '" + address
                                            + "' WHERE (`IDNV` = '"+ id +"')";
        PreparedStatement statement = conn.getConnection().prepareStatement(sql);
        int row = statement.executeUpdate();
        return (row > 0);
    }

    public boolean remove(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM `nhanvien` WHERE ( `IDNV` = '" + id + "' )";
        PreparedStatement statement = conn.getConnection().prepareStatement(sql);
        int row = statement.executeUpdate();
        return (row > 0);
    }
}
