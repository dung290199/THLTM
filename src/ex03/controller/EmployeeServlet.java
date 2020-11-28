package ex03.controller;

import ex03.model.BO.EmployeeService;
import ex03.model.bean.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {

    private EmployeeService employeeService = new EmployeeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        try {
            switch (action){
                case "create":
                    createEmployee(request, response);
                    break;
                case "edit":
                    updateEmployee(request, response);
                    break;
                case "delete":
                    deleteEmployee(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        try {
            switch (action){
                case "create":
                    showCreateForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    showDeleteForm(request, response);
                    break;
                case "view":
                    viewEmployee(request, response);
                    break;
                default:
                    listEmployees(request, response);
                    break;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void viewEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);
        RequestDispatcher dispatcher;
        if(employee == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            request.setAttribute("employee", employee);
            dispatcher = request.getRequestDispatcher("employee/view.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);
        RequestDispatcher dispatcher;
        if (employee == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            this.employeeService.remove(id);
            try {
                response.sendRedirect("/employees");
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);
        RequestDispatcher dispatcher;
        if (employee == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            request.setAttribute("employee", employee);
            dispatcher = request.getRequestDispatcher("employee/delete.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }
//ISO-8859-1
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        int idpb = Integer.parseInt(request.getParameter("idpb"));
        String address = new String(request.getParameter("address").getBytes("ISO-8859-1"), "UTF-8");

        Employee employee = this.employeeService.findById(id);
        RequestDispatcher dispatcher;
        if(employee == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            employee.setName(name);
            employee.setIdpb(idpb);
            employee.setAddress(address);
            this.employeeService.update(id, employee);
            request.setAttribute("employee", employee);
            request.setAttribute("message", "Employee information was updated");
            dispatcher = request.getRequestDispatcher("employee/edit.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);
        RequestDispatcher dispatcher;
        if (employee == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            request.setAttribute("employee", employee);
            dispatcher = request.getRequestDispatcher("employee/edit.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/create.jsp");
        try{
            dispatcher.forward(request,response);
        }catch(ServletException | IOException e){
            e.printStackTrace();
        }
    }

    public void listEmployees(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        List<Employee> employees = this.employeeService.findAll();
        request.setAttribute("employees", employees);

        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/list.jsp");
        try {
            dispatcher.forward(request,response);
        }catch(ServletException | IOException e){
            e.printStackTrace();
        }
    }
    private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        int idpb = Integer.parseInt(request.getParameter("idpb"));
        String address = new String(request.getParameter("address").getBytes("ISO-8859-1"), "UTF-8");

        Employee employee = new Employee(name, idpb, address);
        this.employeeService.create(employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/create.jsp");
        request.setAttribute("message", "New employee was created");
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

}
