package web;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.OperationDao;
import model.dao.IOperationDao;
import model.entities.Operation;

@WebServlet("/operationServlet")
public class OperationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IOperationDao operationDao;

    public void init() {
        operationDao = new OperationDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listOperations(request, response);
                break;
            case "showForm":
                showForm(request, response);
                break;
            case "add":
                addOperation(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateOperation(request, response);
                break;
            case "delete":
                deleteOperation(request, response);
                break;
            default:
                listOperations(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listOperations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Operation> operations = operationDao.listerOperations();
        request.setAttribute("operations", operations);
        request.getRequestDispatcher("liste_operations.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("operation-form.jsp").forward(request, response);
    }

    private void addOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idOperation = Integer.parseInt(request.getParameter("idOperation"));
            String refOperation = request.getParameter("refOperation");
            String natureOperation = request.getParameter("natureOperation");
            Date dateOperation = Date.valueOf(request.getParameter("dateOperation"));
            String notes = request.getParameter("notes");

            Operation operation = new Operation();
            operation.setIdOperation(idOperation);
            operation.setRefOperation(refOperation);
            operation.setNatureOperation(natureOperation);
            operation.setDateOperation(dateOperation);
            operation.setNotes(notes);

            operationDao.ajouterOperation(operation);

            response.sendRedirect("operationServlet?action=list");
        } catch (NumberFormatException  e) {
            e.printStackTrace();
            response.sendRedirect("erreur.jsp");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idOperation = Integer.parseInt(request.getParameter("idOperation"));
        Operation operation = operationDao.trouverOperationParId(idOperation);
        request.setAttribute("operation", operation);
        request.getRequestDispatcher("operation-form.jsp").forward(request, response);
    }

    private void updateOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idOperation = Integer.parseInt(request.getParameter("idOperation"));
            String refOperation = request.getParameter("refOperation");
            String natureOperation = request.getParameter("natureOperation");
            Date dateOperation = Date.valueOf(request.getParameter("dateOperation"));
            String notes = request.getParameter("notes");

            Operation operation = new Operation();
            operation.setIdOperation(idOperation);
            operation.setRefOperation(refOperation);
            operation.setNatureOperation(natureOperation);
            operation.setDateOperation(dateOperation);
            operation.setNotes(notes);

            operationDao.mettreAJourOperation(operation);

            response.sendRedirect("operationServlet?action=list");
        } catch (NumberFormatException  e) {
            e.printStackTrace();
            response.sendRedirect("erreur.jsp");
        }
    }

    private void deleteOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idOperation = Integer.parseInt(request.getParameter("idOperation"));
        operationDao.supprimerOperation(idOperation);

        response.sendRedirect("operationServlet?action=list");
    }
}
