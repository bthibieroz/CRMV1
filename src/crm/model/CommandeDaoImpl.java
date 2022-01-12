package crm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;
import fr.cleverdev.model.Auteur;

public class CommandeDaoImpl implements CommandeDao {
	
	/*private static final String SQL_INSERT       = "INSERT INTO auteur(nom,prenom,telephone,email) VALUES(?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id,nom,prenom,telephone,email FROM auteur";
    private static final String SQL_SELECT_BY_ID = "SELECT id,nom,prenom,telephone,email FROM auteur WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM auteur WHERE id = ? ";
	private static final String SQL_UPDATE_BY_ID  = "UPDATE Auteur SET nom = ?, prenom= ?, telephone= ?, email= ? WHERE id = ?";
	*/
	
	//Optionnels
	private static final String SQL_SELECT_BY_CLIENT = "SELECT id, label, tjmHT,dureeJours,TVA,statut,typeCommande,notes FROM Commandes WHERE idclient= ? ";
	private static final String SQL_SELECT_ORDER_BY_CLIENT = "SELECT id, label, tjmHT,dureeJours,TVA,statut,typeCommande,notes FROM Commandes ORDER BY idClients";
	
	private static final String SQL_SELECT_ORDER_BY_TYPE = "SELECT id, label, tjmHT,dureeJours,TVA,statut,typeCommande,notes FROM Commandes ORDER BY typeCommande";
	
	private static final String SQL_FIND_AND_SELECT_ORDER_BY_LABEL = "SELECT id, label, tjmHT,dureeJours,TVA,statut,typeCommande,notes FROM Commandes ORDER BY label";
	
	private DaoFactory factory;
	
	public CommandeDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}
	
	
	@Override
	public Commande trouver(long id) throws DaoException {
		Commande          commande=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_CLIENT );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  commande = map(rs);
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Commande non trouvée. Verifiez que l'id est juste", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return commande;
	}
	
	@Override 
	public Liste<Commande> listerParClient () throws DaoException {
		Liste<Commande> listeCommandes= new ArrayList<Commandes>();
		Connection con=null ; 
		try {
			con=factory.getConnection();
			PreparedStatement 	pst=con.PrepareStatement(SQL_SELECT_ORDER_BY_CLIENT);
			ResultSet 			rs= pst.executeQuery();
			while ( rs.next() ) {
		    	  listeCommandes.add( map(rs) );
		    	//affichage des clients
		    	  listeCommandes.setClient()
		      }
			
			rs.close();
		    pst.close();
		} catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture des commandes", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeCommandes;
	}
	
	@Override 
	public Liste<Commande> listerParType () throws DaoException {
		Liste<Commande> listeCommandes= new ArrayList<Commandes>();
		Connection con=null;
		try {
			con=factory.getConnection();
			PreparedStatement 	pst=con.PrepareStatement(SQL_SELECT_ORDER_BY_TYPE);
			ResultSet 			rs= pst.executeQuery();
			while ( rs.next() ) {
		    	  listeCommandes.add( map(rs) );
		    	//affichage des clients
		    	  
		      }
			
			rs.close();
		    pst.close();
		} catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture des commandes", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeCommandes;
	}
	
	@Override 
	public Liste<Commande> listerParLabel () throws DaoException {
		Liste<Commande> listeCommandes= new ArrayList<Commandes>();
		Connection con=null;
		try {
			con=factory.getConnection();
			PreparedStatement 	pst=con.PrepareStatement(SQL_FIND_AND_SELECT_ORDER_BY_LABEL);
			ResultSet 			rs= pst.executeQuery();
			while ( rs.next() ) {
		    	  listeCommandes.add( map(rs) );
		    	//affichage des clients
		    	  listeCommandes.setClient()
		      }
			rs.close();
		    pst.close();
		} catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture des commandes", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeCommandes;
	}

}
