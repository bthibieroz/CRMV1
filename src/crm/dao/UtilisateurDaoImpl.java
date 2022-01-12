package crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crm.model.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private static final String SQL_INSERT       = "INSERT INTO utilisateurs (login,motdepasse,email) VALUES(?,?,?)";
	private static final String SQL_SELECT       = "SELECT id,login,motdepasse,email FROM utilisateurs";
    private static final String SQL_SELECT_BY_ID = "SELECT id,login,motdepasse,email FROM utilisateurs WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM utilisateurs WHERE id = ? ";
	private static final String SQL_UPDATE_BY_ID = "UPDATE utilisateurs SET login=?,motdepasse=?,email=? WHERE id = ?";


	private DaoFactory factory;

	public UtilisateurDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Utilisateur utilisateur) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );

			pst.setString( 1, utilisateur.getLogin() );
			pst.setString( 2, utilisateur.getMotDePasse() );
			pst.setString( 3, utilisateur.getEmail() );

			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec création Utilisateur (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                utilisateur.setId( rsKeys.getLong( 1 ) );
            } else {
                throw new DaoException( "Echec création Utilisateur (ID non retourné)" );
            }
            rsKeys.close();
			pst.close();

	    } catch(SQLException ex) {
	    	throw new DaoException("Echec création Utilisateur",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}

	}

	@Override
	public Utilisateur trouver(long id) throws DaoException {
		Utilisateur           utilisateur=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  utilisateur = map(rs);
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD Utilisateur", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> lister() throws DaoException {
		List<Utilisateur> listeUtilisateur = new ArrayList<>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeUtilisateur.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Utilisateur", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeUtilisateur;
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
				  throw new DaoException("Erreur de suppression Utilisateur("+id+")");
			  }
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Utilisateur", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}

	}

	@Override
	public void modifier(Utilisateur utilisateur) throws DaoException {
		Connection con = null;
		PreparedStatement pst=null;

		try {
			con = factory.getConnection();
			pst = con.prepareStatement (SQL_UPDATE_BY_ID);

			pst.setString( 1, utilisateur.getLogin() );
			pst.setString( 2, utilisateur.getMotDePasse() );
			pst.setString( 3, utilisateur.getEmail() );
			pst.setLong(4, utilisateur.getId());

			int statut = pst.executeUpdate();
			if ( statut == 0 ) {
				  throw new DaoException("Erreur de modification de le Utilisateur("+utilisateur.getId()+")");
			  }

			pst.close();

		} catch(SQLException ex) {
			throw new DaoException("Echec modification Auteur",ex);
		}
		finally {
			factory.releaseConnection(con);
		}

	}

	private static Utilisateur map( ResultSet resultSet ) throws SQLException {
        Utilisateur u = new Utilisateur();
        u.setId( resultSet.getLong( "id" ) );
        u.setLogin( resultSet.getString( "login" ) );
        u.setMotDePasse( resultSet.getString( "motdepasse" ) );
        u.setEmail( resultSet.getString( "email" ) );
        return u;
    }

}
