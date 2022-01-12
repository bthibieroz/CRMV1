package crm.launcher;

import java.util.Iterator;
import java.util.List;

import crm.dao.DaoException;
import crm.dao.DaoFactory;
import crm.dao.UtilisateurDao;
import crm.model.Utilisateur;
//import crm.dao.UtilisateurDaoImpl;

public class LauncherTestUtilisateur {

	public static void main(String[] args) {
		
		UtilisateurDao utilisateurDao = DaoFactory.getInstance().getUtilisateurDao();
		List<Utilisateur> listUtilisateur;
		Utilisateur tmp;
		
		// creer des utilisateurs
		/*
		try {
			utilisateurDao.creer(new Utilisateur("logingutil3", "mot1","utlisateur3@gmail.com"));
			listUtilisateur = utilisateurDao.lister();
			
		    Iterator<Utilisateur> i = listUtilisateur.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// Trouver utilisateurs
		/*
		try {
			tmp=utilisateurDao.trouver(1);
			System.out.println(tmp);
			//utilisateurDao.lister();
			} catch (DaoException e) {
				e.printStackTrace();
				} 
		*/
		// Lister des utilisateurs
		
		/*
		try {
			listUtilisateur = utilisateurDao.lister();
			
		    Iterator<Utilisateur> i = listUtilisateur.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			e.printStackTrace();
		}
		*/
		
		// Supprimer utilisteurs
		/*
		try {
			 utilisateurDao.supprimer(1);
			 //utilisateurDao.lister();
			} catch (DaoException e) {
				e.printStackTrace();
				}	
		*/
		
		// Modifier utilisateurs
		/*
		try {
			//utilisateurDao.modifier(tmp);
			//System.out.println(tmp);
			tmp=utilisateurDao.trouver(6);
			System.out.println(tmp);
			tmp.setEmail("utlisateur5@gmail.com");
			utilisateurDao.modifier(tmp);
		  
		} catch (DaoException e) {
			e.printStackTrace();
		}  
		*/

	}

}
