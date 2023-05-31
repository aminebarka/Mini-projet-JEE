package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.FournisseurDao;
import model.dao.IFournisseurDao;
import model.entities.Fournisseur;

@WebServlet("/fournisseurServlet")
public class FournisseurServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IFournisseurDao fournisseurDao;

    public void init() {
        fournisseurDao = new FournisseurDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listFournisseurs(request, response);
                break;
            case "showForm":
                showForm(request, response);
                break;
            case "add":
                addFournisseur(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateFournisseur(request, response);
                break;
            case "delete":
                deleteFournisseur(request, response);
                break;
            default:
                listFournisseurs(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listFournisseurs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fournisseur> fournisseurs = fournisseurDao.listerFournisseurs();
        request.setAttribute("fournisseurs", fournisseurs);
        request.getRequestDispatcher("fournisseur-list.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("fournisseur-form.jsp").forward(request, response);
    }

    private void addFournisseur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idFournisseur = Integer.parseInt(request.getParameter("idFournisseur"));
            String nomFournisseur = request.getParameter("nomFournisseur");
            int contact = Integer.parseInt(request.getParameter("contact"));
            String adresseMail = request.getParameter("adresseMail");

            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setIdFournisseur(idFournisseur);
            fournisseur.setNomFournisseur(nomFournisseur);
            fournisseur.setContact(contact);
            fournisseur.setAdresseMail(adresseMail);

            fournisseurDao.ajouterFournisseur(fournisseur);

            response.sendRedirect("fournisseurServlet?action=list");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("erreur.jsp");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idFournisseur = Integer.parseInt(request.getParameter("idFournisseur"));
        Fournisseur fournisseur = fournisseurDao.trouverFournisseurParId(idFournisseur);
        request.setAttribute("fournisseur", fournisseur);
        request.getRequestDispatcher("fournisseur-form.jsp").forward(request, response);
    }

    private void updateFournisseur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idFournisseur = Integer.parseInt(request.getParameter("idFournisseur"));
            String nomFournisseur = request.getParameter("nomFournisseur");
            int contact = Integer.parseInt(request.getParameter("contact"));
            String adresseMail = request.getParameter("adresseMail");

            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setIdFournisseur(idFournisseur);
            fournisseur.setNomFournisseur(nomFournisseur);
            fournisseur.setContact(contact);
            fournisseur.setAdresseMail(adresseMail);

            fournisseurDao.mettreAJourFournisseur(fournisseur);

            response.sendRedirect("fournisseurServlet?action=list");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("erreur.jsp");
        }
    }

    private void deleteFournisseur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idFournisseur = Integer.parseInt(request.getParameter("idFournisseur"));
        fournisseurDao.supprimerFournisseur(idFournisseur);

        response.sendRedirect("fournisseurServlet");
    }
}
