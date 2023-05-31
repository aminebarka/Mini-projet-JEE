package model.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entities.Fournisseur;

public class FournisseurDao implements IFournisseurDao {

    private Connection connection;

    public FournisseurDao() {
        connection = SingletonConnection.getConnection();
    }

    @Override
    public void ajouterFournisseur(Fournisseur fournisseur) {
        try {
            String query = "INSERT INTO Fournisseur (idfournisseur, nomfournisseur, contact, adressemail) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, fournisseur.getIdFournisseur());
            preparedStatement.setString(2, fournisseur.getNomFournisseur());
            preparedStatement.setInt(3, fournisseur.getContact());
            preparedStatement.setString(4, fournisseur.getAdresseMail());

            preparedStatement.executeUpdate();
            System.out.println("Fournisseur ajouté avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du fournisseur : " + e.getMessage());
        }
    }

    @Override
    public void mettreAJourFournisseur(Fournisseur fournisseur) {
        try {
            String query = "UPDATE Fournisseur SET nomfournisseur=?, contact=?, adressemail=? WHERE idfournisseur=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fournisseur.getNomFournisseur());
            preparedStatement.setInt(2, fournisseur.getContact());
            preparedStatement.setString(3, fournisseur.getAdresseMail());
            preparedStatement.setInt(4, fournisseur.getIdFournisseur());

            preparedStatement.executeUpdate();
            System.out.println("Fournisseur mis à jour avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du fournisseur : " + e.getMessage());
        }
    }

    @Override
    public void supprimerFournisseur(int idFournisseur) {
        try {
            String query = "DELETE FROM Fournisseur WHERE idfournisseur=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idFournisseur);

            preparedStatement.executeUpdate();
            System.out.println("Fournisseur supprimé avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du fournisseur : " + e.getMessage());
        }
    }

    @Override
    public Fournisseur trouverFournisseurParId(int idFournisseur) {
        Fournisseur fournisseur = null;
        try {
            String query = "SELECT * FROM Fournisseur WHERE idfournisseur=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idFournisseur);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fournisseur = new Fournisseur();
                fournisseur.setIdFournisseur(resultSet.getInt("idfournisseur"));
                fournisseur.setNomFournisseur(resultSet.getString("nomfournisseur"));
                fournisseur.setContact(resultSet.getInt("contact"));
                fournisseur.setAdresseMail(resultSet.getString("adressemail"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du fournisseur : " + e.getMessage());
        }
        return fournisseur;
    }

    @Override
    public List<Fournisseur> listerFournisseurs() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        try {
            String query = "SELECT * FROM Fournisseur";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setIdFournisseur(resultSet.getInt("idfournisseur"));
                fournisseur.setNomFournisseur(resultSet.getString("nomfournisseur"));
                fournisseur.setContact(resultSet.getInt("contact"));
                fournisseur.setAdresseMail(resultSet.getString("adressemail"));

                fournisseurs.add(fournisseur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la liste des fournisseurs : " + e.getMessage());
        }
        return fournisseurs;
    }
}
