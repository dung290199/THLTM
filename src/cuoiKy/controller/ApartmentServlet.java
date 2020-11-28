package cuoiKy.controller;

import cuoiKy.model.BO.ApartmentService;
import cuoiKy.model.bean.Apartment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ApartmentServlet", urlPatterns = "/apartments")
public class ApartmentServlet extends HttpServlet {

    private ApartmentService apartmentService = new ApartmentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        try {
            switch (action){
                case "create":
                    createApartment(request, response);
                    break;
                case "edit":
                    updateApartment(request, response);
                    break;
                case "delete":
                    deleteApartment(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
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
                    viewApartment(request, response);
                    break;
                default:
                    listApartments(request, response);
                    break;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void viewApartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Apartment apartment = this.apartmentService.findById(id);
        RequestDispatcher dispatcher;
        if(apartment == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            request.setAttribute("apartment", apartment);
            dispatcher = request.getRequestDispatcher("apartment/view.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void deleteApartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Apartment apartment = this.apartmentService.findById(id);
        RequestDispatcher dispatcher;
        if (apartment == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            this.apartmentService.remove(id);
            try {
                response.sendRedirect("/apartments");
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Apartment apartment = this.apartmentService.findById(id);
        RequestDispatcher dispatcher;
        if (apartment == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            request.setAttribute("apartment", apartment);
            dispatcher = request.getRequestDispatcher("apartment/delete.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void updateApartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        int idpb = Integer.parseInt(request.getParameter("id"));
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
        String description = new String(request.getParameter("description").getBytes("ISO-8859-1"),"UTF-8");

        Apartment apartment = this.apartmentService.findById(idpb);
        RequestDispatcher dispatcher;
        if(apartment == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            apartment.setId(idpb);
            apartment.setName(name);
            apartment.setDescription(description);
            this.apartmentService.update(idpb, apartment);
            request.setAttribute("apartment", apartment);
            request.setAttribute("message", "Apartment information was updated");
            request.setCharacterEncoding("utf-8");
            dispatcher = request.getRequestDispatcher("apartment/edit.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        int id = Integer.parseInt(request.getParameter("id"));
        Apartment apartment = this.apartmentService.findById(id);
        RequestDispatcher dispatcher;
        if (apartment == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            request.setAttribute("apartment", apartment);
            dispatcher = request.getRequestDispatcher("apartment/edit.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("apartment/create.jsp");
        try{
            dispatcher.forward(request,response);
        }catch(ServletException | IOException e){
            e.printStackTrace();
        }
    }

    public void listApartments(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        List<Apartment> apartments = this.apartmentService.findAll();
        request.setAttribute("apartments", apartments);

        RequestDispatcher dispatcher = request.getRequestDispatcher("apartment/list.jsp");
        try {
            dispatcher.forward(request,response);
        }catch(ServletException | IOException e){
            e.printStackTrace();
        }
    }
    private void createApartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        String description = new String(request.getParameter("description").getBytes("ISO-8859-1"), "UTF-8");

        Apartment apartment = new Apartment(name, description);
        this.apartmentService.create(apartment);
        RequestDispatcher dispatcher = request.getRequestDispatcher("apartment/create.jsp");
        request.setAttribute("message", "New apartment was created");
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

}
