package cuoiKy.model.BO;

import cuoiKy.model.DAO.ApartmentDAO;
import cuoiKy.model.bean.Apartment;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentService {

    private ApartmentDAO apartmentDAO = new ApartmentDAO();

    public List<Apartment> findAll() throws SQLException, ClassNotFoundException {
        List<Apartment> result = new ArrayList<>();
        ResultSet resultSet = this.apartmentDAO.getAll();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            result.add(new Apartment(id, name, description));
        }
        return result;
    }

    public boolean create (Apartment Apartment) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        return this.apartmentDAO.create(Apartment);
    }

    public Apartment findById(int id) throws SQLException, ClassNotFoundException {
        return this.apartmentDAO.findById(id);
    }

    public List<Apartment> findByName(String name) throws SQLException, ClassNotFoundException {
        return this.apartmentDAO.findByName(name);
    }

    public boolean update(int id, Apartment Apartment) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        return this.apartmentDAO.update(id, Apartment);
    }

    public boolean remove(int id) throws SQLException, ClassNotFoundException {
        return this.apartmentDAO.remove(id);
    }
}
