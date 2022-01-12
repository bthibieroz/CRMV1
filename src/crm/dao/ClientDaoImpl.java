package crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import crm.model.Client;


public class ClientDaoImpl implements ClientDao{
	
	private static final String SQL_INSERT       = "INSERT INTO (nom,prenom,entreprise,email,telephone,actif) VALUES(?,?,?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id ,nom, prenom, entreprise, email, telephone, actif  FROM clients";
    private static final String SQL_SELECT_BY_ID = "SELECT id ,nom, prenom, entreprise, email, telephone, actif  FROM clients WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM clients WHERE id = ? ";
	private static final String SQL_UPDATE_BY_ID = "UPDATE clients SET nom=?,prenom=?,entreprise=?,email=?, telephone=?,actif=? WHERE id = ?";
	private static final String SQL_SELECT_BY_NOM = "SELECT id ,nom, prenom, entreprise, email, telephone, actif  FROM clients WHERE nom = ?";
	
	private DaoFactory factory;
	
	public ClientDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Client client) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );

			pst.setString( 1, client.getNom() );
			pst.setString( 2, client.getPrenom() );
			pst.setString( 3, client.getEntreprise() );
			pst.setString( 4, client.getEmail() );
			pst.setString( 5, client.getTelephone() );
			pst.setBoolean( 6, client.getActif() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec création Client (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                client.setId( rsKeys.getLong( 1 ) );
            } else {
                throw new DaoException( "Echec création Client (ID non retourné)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec création Client",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public Client trouver(long id) throws DaoException {
		Client           client=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  client = map(rs);
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD Client", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return client;
	}
	
	@Override
	public Client trouver(String nom) throws DaoException {
		Client           client=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_NOM );
			  pst.setString(2, nom);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  client = map(rs);
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD Client", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return client;
	}

	@Override
	public List<Client> lister() throws DaoException {
		List<Client> listeClient = new ArrayList<Client>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeClient.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Client", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeClient;
	}

	@Override
	public void supprimer(long id) throws DaoException {
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_DELETE_BY_ID );
			  pst.setLong(1, id);
			  int statut = pst.executeUpdate();
			  if ( statut == 0 ) {
				  throw new DaoException("Erreur de suppression Client("+id+")");
			  }
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Client", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public void modifier(Client client) throws DaoException {
		Connection con = null; 
		PreparedStatement pst=null;
		
		try {
			con = factory.getConnection();
			pst = con.prepareStatement (SQL_UPDATE_BY_ID);

			pst.setString( 1, client.getNom() );
			pst.setString( 2, client.getPrenom() );
			pst.setString( 3, client.getEntreprise() );
			pst.setString( 4, client.getEmail() );
			pst.setString( 5, client.getTelephone() );
			pst.setBoolean( 6, client.getActif() );
			pst.setLong(7, client.getId());

			int statut = pst.executeUpdate();
			if ( statut == 0 ) {
				  throw new DaoException("Erreur de modification de le Client("+client.getId()+")"); 
			  }
			
			pst.close();
			
		} catch(SQLException ex) {
			throw new DaoException("Echec modification Auteur",ex);
		}
		finally { 	
			factory.releaseConnection(con);
		}
		
	}

    private static Client map( ResultSet resultSet ) throws SQLException {
        Client c = new Client();
        c.setId( resultSet.getLong( "id" ) );
        c.setNom( resultSet.getString( "nom" ) );
        c.setPrenom( resultSet.getString( "prenom" ) );
        c.setEntreprise( resultSet.getString( "entreprise" ) );
        c.setEmail( resultSet.getString( "email" ) );
        c.setTelephone( resultSet.getString( "telephone" ) );
        c.setActif( resultSet.getBoolean( "actif" ) );
        
        return c;
    }
}
