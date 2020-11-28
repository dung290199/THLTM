package ex03.model.DAO;

import ex03.model.bean.Apartment;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDAO {
    private ConnectionMySQL conn = new ConnectionMySQL();;

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `phongban` LIMIT 0, 10";
        PreparedStatement statement = this.conn.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public boolean create (Apartment apartment) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = "INSERT INTO `phongban`(Tenpb, MoTa) VALUES (?, ?)";
        String name = apartment.getName();
        String description = apartment.getDescription();

        PreparedStatement statement = this.conn.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, description);

        int row = statement.executeUpdate();
        return (row > 0);
    }

    public Apartment findById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `phongban` WHERE (`IDPB` = '" + id + "')";

        PreparedStatement statement = this.conn.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        resultSet.next();
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);

        Apartment Apartment = new Apartment(id, name, description);
        return Apartment;
    }

    public List<Apartment> findByName(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `phongban` WHERE (`Tenpb` LIKE '%" + name + "%')";

        PreparedStatement statement = this.conn.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Apartment> result = new ArrayList<>();
        while ( resultSet.next() ){
            int id = resultSet.getInt(1);
            String nameTemp = resultSet.getString(2);
            String description = resultSet.getString(3);
            result.add( new Apartment(id, name, description) );
        }
        return result;
    }

    public boolean update(int id, Apartment apartment) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String name = apartment.getName();
        String description = apartment.getDescription();

        String sql = "UPDATE `phongban` SET `Tenpb` = '"+ name
                + "', `MoTa` = '" + description
                + "' WHERE (`IDPB` = '"+ id +"')";
        PreparedStatement statement = conn.getConnection().prepareStatement(sql);
        int row = statement.executeUpdate();
        return (row > 0);
    }

    public boolean remove(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM `phongban` WHERE ( `IDPB` = '" + id + "' )";
        PreparedStatement statement = conn.getConnection().prepareStatement(sql);
        int row = statement.executeUpdate();
        return (row > 0);
    }
}
