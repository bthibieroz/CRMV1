package crm.launcher;

import java.util.Iterator;
import java.util.List;
import crm.dao.ClientDao;
import crm.dao.DaoFactory;
import crm.dao.UtilisateurDao;
import crm.model.Client;
import crm.model.Commande;
import crm.model.Utilisateur;
import crm.dao.CommandeDao;
import crm.dao.DaoException;

public class Launcher {
	public static void main(String[] args) {
	
		ClientDao clientDao = DaoFactory.getInstance().getClientDao();
		CommandeDao commandeDao = DaoFactory.getInstance().getCommandeDao();
		UtilisateurDao utilisateurDao = DaoFactory.getInstance().getUtilisateurDao();
		List<Client> listeClient;
		List<Commande> listeCommande;
		List<Utilisateur> listeUtilisateur;
		int compteur = 0;
		
		try {
			listeClient = clientDao.lister();
			Iterator<Client> i = listeClient.iterator();
			
			while (i.hasNext()) {
				compteur ++;
				System.out.println(i.next());
			}
			System.out.println(" Il y'a " + compteur + " Clients dans la liste.");
		}catch (DaoException e) {
			e.printStackTrace();
		}
	}
}
