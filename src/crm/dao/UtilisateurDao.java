package crm.dao;

import java.util.List;

import crm.model.Utilisateur;

public interface UtilisateurDao {


	void         creer( Utilisateur utilisateur ) throws DaoException;

    Utilisateur       trouver( long id ) throws DaoException;

    List<Utilisateur> lister() throws DaoException;

    void         supprimer( long id ) throws DaoException;

    void		 modifier(Utilisateur utilisateur) throws DaoException;

}
