package crm.dao;

import java.util.List;
import crm.model.Client;

public interface ClientDao {
	
	void         creer( Client client ) throws DaoException;

    Client       trouver( long id ) throws DaoException;
    Client       trouver( String nom ) throws DaoException;
    

    List<Client> lister() throws DaoException;

    void         supprimer( long id ) throws DaoException;

    void		 modifier(Client client) throws DaoException;

}
