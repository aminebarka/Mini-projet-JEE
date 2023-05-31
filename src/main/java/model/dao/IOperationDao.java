package model.dao;

import java.util.List;
import model.entities.Operation;

public interface IOperationDao {
    void ajouterOperation(Operation operation);
    
    void mettreAJourOperation(Operation operation);
    
    void supprimerOperation(int idOperation);
    
    Operation trouverOperationParId(int idOperation);
    
    List<Operation> listerOperations();
}