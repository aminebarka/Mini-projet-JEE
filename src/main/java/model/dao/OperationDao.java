package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Operation;

public class OperationDao implements IOperationDao {

    private Connection connection;

    public OperationDao() {
        connection = SingletonConnection.getConnection();
    }

    @Override
    public void ajouterOperation(Operation operation) {
        try {
            String query = "INSERT INTO Operation (idOperation, refOperation, natureOperation, dateOperation, notes) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, operation.getIdOperation());
            preparedStatement.setString(2, operation.getRefOperation());
            preparedStatement.setString(3, operation.getNatureOperation());
            preparedStatement.setDate(4, new java.sql.Date(operation.getDateOperation().getTime()));
            preparedStatement.setString(5, operation.getNotes());

            preparedStatement.executeUpdate();
            System.out.println("Opération ajoutée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'opération : " + e.getMessage());
        }
    }

    @Override
    public void mettreAJourOperation(Operation operation) {
        try {
            String query = "UPDATE Operation SET refOperation=?, natureOperation=?, dateOperation=?, notes=? WHERE idOperation=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, operation.getRefOperation());
            preparedStatement.setString(2, operation.getNatureOperation());
            preparedStatement.setDate(3, new java.sql.Date(operation.getDateOperation().getTime()));
            preparedStatement.setString(4, operation.getNotes());
            preparedStatement.setInt(5, operation.getIdOperation());

            preparedStatement.executeUpdate();
            System.out.println("Opération mise à jour avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'opération : " + e.getMessage());
        }
    }

    @Override
    public void supprimerOperation(int idOperation) {
        try {
            String query = "DELETE FROM Operation WHERE idOperation=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idOperation);

            preparedStatement.executeUpdate();
            System.out.println("Opération supprimée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'opération : " + e.getMessage());
        }
    }

    @Override
    public Operation trouverOperationParId(int idOperation) {
        Operation operation = null;
        try {
            String query = "SELECT * FROM Operation WHERE idOperation=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idOperation);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                operation = new Operation();
                operation.setIdOperation(resultSet.getInt("idOperation"));
                operation.setRefOperation(resultSet.getString("refOperation"));
                operation.setNatureOperation(resultSet.getString("natureOperation"));
                operation.setDateOperation(resultSet.getDate("dateOperation"));
                operation.setNotes(resultSet.getString("notes"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'opération : " + e.getMessage());
        }
        return operation;
    }

    @Override
    public List<Operation> listerOperations() {
        List<Operation> operations = new ArrayList<>();
        try {
            String query = "SELECT * FROM Operation";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Operation operation = new Operation();
                operation.setIdOperation(resultSet.getInt("idOperation"));
                operation.setRefOperation(resultSet.getString("refOperation"));
                operation.setNatureOperation(resultSet.getString("natureOperation"));
                operation.setDateOperation(resultSet.getDate("dateOperation"));
                operation.setNotes(resultSet.getString("notes"));

                operations.add(operation);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la liste des opérations : " + e.getMessage());
        }
        return operations;
    }
}
