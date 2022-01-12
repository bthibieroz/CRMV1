package crm.dao;

import java.util.List;

import crm.model.Commande;

public interface CommandeDao {
	void         creer( Commande commande ) throws DaoException;

    Commande       trouver( long id ) throws DaoException;

    List<Commande> lister() throws DaoException;

    void         supprimer( long id ) throws DaoException;

    void		 modifier(Commande commande) throws DaoException;
    
    Commande 	trouverParClient(long id) throws DaoException;
    
    List<Commande> listerParClient() throws DaoException;
    
    List<Commande> listerParType() throws DaoException;
    
    List<Commande> listerParLabel() throws DaoException;

}
