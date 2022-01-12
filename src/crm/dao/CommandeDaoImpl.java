package crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

import crm.model.Commande;
import crm.model.Statut;
import crm.model.Commande;
import crm.model.TypeCommande;

public class CommandeDaoImpl implements CommandeDao {
	private static final String SQL_INSERT       = "INSERT INTO Commandes (label,tjmHT,dureeJours,TVA,statut,typecommande,notes, idclient) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id ,label,tjmHT,dureeJours,TVA,statut,typecommande,notes,idclient FROM commandes";
    private static final String SQL_SELECT_BY_ID = "SELECT id,label,tjmHT,dureeJours,TVA,statut,typecommande,notes, idclient FROM commandes WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM commandes WHERE id = ? ";
	private static final String SQL_UPDATE_BY_ID = "UPDATE commandes SET label= ?,tjmHT=?,dureeJours= ?,TVA = ?,statut = ?,typecommande = ?,notes = ? , idclient= ? WHERE id= ?";
	
	private DaoFactory factory;
	
	public CommandeDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Commande commande) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );

			pst.setString( 1, commande.getLabel() );
			pst.setFloat( 2, commande.getTjmHT() );
			pst.setFloat( 3, commande.getDureeJours() );
			pst.setFloat( 4, commande.getTVA());
			pst.setString( 5, commande.getStatut().toString() );
			pst.setString( 6, commande.getTypeCommande().toString());
			pst.setString( 7, commande.getNotes() );
			pst.setLong( 8, commande.getClient().getId());
			
			
			
	
			int stat = pst.executeUpdate();

            if ( stat == 0 ) {
                throw new DaoException( "Echec création commande (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                commande.setId( rsKeys.getLong(1) );
            } else {
                throw new DaoException( "Echec création commande (ID non retourné)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec création commande",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public Commande trouver(long id) throws DaoException {  
		Commande commande=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  commande = map(rs);
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD commande", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return commande;
	}

	@Override
	public List<Commande> lister() throws DaoException {
		List<Commande> listeCommande = new ArrayList<Commande>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeCommande.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Commande", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeCommande;
		
	}

	@Override
	public void supprimer(long id) throws DaoException {
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_DELETE_BY_ID );
			  pst.setLong(1, id);
			  int stat = pst.executeUpdate();
			  if ( stat == 0 ) {
				  throw new DaoException("Erreur de suppression Commande("+id+")");
			  }
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Commande", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public void modifier(Commande commande) throws DaoException {
		
		Connection con = null; 
		PreparedStatement pst=null;
		
		try {
			con = factory.getConnection();
			pst = con.prepareStatement (SQL_UPDATE_BY_ID);
		
			
			pst.setString( 1, commande.getLabel() );
			pst.setFloat( 2, commande.getTjmHT() );
			pst.setFloat( 3, commande.getDureeJours() );
			pst.setFloat( 4, commande.getTVA());
			pst.setString( 5, commande.getStatut().toString() );
			pst.setString( 6, commande.getTypeCommande().toString());
			pst.setString( 7, commande.getNotes() );
			pst.setLong( 8, commande.getClient().getId());
			pst.setLong(9, commande.getId());

			int stat = pst.executeUpdate();
			if ( stat == 0 ) {
				  throw new DaoException("Erreur de modification de le Commande("+commande.getId()+")"); 
			  }
			
			pst.close();
			
		} catch(SQLException ex) {
			throw new DaoException("Echec modification Commande",ex);
		}
		finally { 	
			factory.releaseConnection(con);
		}
		
		
	}
	
	
	 private static Commande map( ResultSet resultSet ) throws SQLException, DaoException {
		    ClientDao clientDao = DaoFactory.getInstance().getClientDao();
	        Commande c = new Commande();
	        c.setId( resultSet.getLong("id"));
	        c.setLabel( resultSet.getString( "label" ) );
	        c.setTjmHT( resultSet.getFloat( "tjmHT" ) );
	        c.setDureeJours( resultSet.getFloat( "dureejours" ) );
	        c.setTVA( resultSet.getFloat( "TVA" ) );
	        
	        for(TypeCommande s : TypeCommande.values()) {
	        	if(resultSet.getString("typeCommande").equals(s.toString())) {
	        		c.setTypeCommande(s);
	        	}
	        }
	       
	        for(Statut s : Statut.values()) {
	        	if(resultSet.getString("statut").equals(s.toString())) {
	        		c.setStatut(s);
	        	}
	        }
	        
	        c.setNotes( resultSet.getString( "notes" ) );
	        c.setClient( clientDao.trouver( resultSet.getLong("idclient") ));
	        
	        return c;
	    }

}
