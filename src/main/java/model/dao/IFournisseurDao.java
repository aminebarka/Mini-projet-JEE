package model.dao;

import java.util.List;

import model.entities.Fournisseur;

public interface IFournisseurDao {
    void ajouterFournisseur(Fournisseur fournisseur);
    void mettreAJourFournisseur(Fournisseur fournisseur);
    void supprimerFournisseur(int idFournisseur);
    Fournisseur trouverFournisseurParId(int idFournisseur);
    List<Fournisseur> listerFournisseurs();
}
