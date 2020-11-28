package cuoiKy.controller;

import cuoiKy.model.BO.ApartmentService;
import cuoiKy.model.BO.EmployeeService;
import cuoiKy.model.bean.Apartment;
import cuoiKy.model.bean.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    private EmployeeService employeeService = new EmployeeService();
    private ApartmentService apartmentService = new ApartmentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = new String(request.getParameter("search").getBytes("ISO-8859-1"), "UTF-8");
        try {
            int id = Integer.parseInt(search);
            findWithANumber(id, request, response);
        } catch (NumberFormatException numberFormatException){
            findWithAString(search, request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    }

    private void findWithAString(String search, HttpServletRequest request, HttpServletResponse response){
        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) this.employeeService.findByName(search);
            if (employees.size() != 0){
                goEmployeeList(employees, request, response);
            }else {
                ArrayList<Apartment> apartments = (ArrayList<Apartment>) this.apartmentService.findByName(search);
                if (apartments.size() == 0)
                    goHome(request, response);
                else
                    goApartmentList(apartments, request, response);
            }
        } catch (ClassNotFoundException |SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void findWithANumber(int id, HttpServletRequest request, HttpServletResponse response) {
        try {
            Employee employee = (Employee) this.employeeService.findById(id);
            if (employee != null){
                goEmployeeView(employee, request, response);
            }else {
                Apartment apartment = (Apartment) this.apartmentService.findById(id);
                if ((apartment == null)) {
                    goHome(request, response);
                } else {
                    goApartmentView(apartment, request, response);
                }

            }
        } catch (ClassNotFoundException |SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void goHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }

    private void goEmployeeView(Employee employee, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/view.jsp");
        request.setAttribute("employee", employee);
        dispatcher.forward(request, response);
    }

    private void goEmployeeList(ArrayList<Employee> employees, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/list.jsp");
        request.setAttribute("employees", employees);
        dispatcher.forward(request, response);
    }

    private void goApartmentView(Apartment apartment, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("apartment/view.jsp");
        request.setAttribute("apartment", apartment);
        dispatcher.forward(request, response);
    }

    private void goApartmentList(ArrayList<Apartment> apartments, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("apartment/list.jsp");
        request.setAttribute("apartments", apartments);
        dispatcher.forward(request, response);
    }

}
