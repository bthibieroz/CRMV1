package crm.launcher;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
		boolean pgr = true;
		Scanner sc = new Scanner(System.in);
		String choix;
		
		System.out.println(" ===============");
		System.out.println(" == BIENVENUE == ");
		System.out.println(" ===============\n");
		
		while(pgr) {
			
			do {
			
				System.out.println(" Que souhaitez vous faire ?\n");
				System.out.println(" Aller dans le menu COMMANDES, entrez 'COM'");
				System.out.println(" Aller dans le menu CLIENTS, entrez 'CLI'");
				System.out.println(" Quittez ? entrez 'q'\n");
				
				choix = sc.nextLine();
				
			}while(!(choix.equalsIgnoreCase("q")) && !(choix.equalsIgnoreCase("com")) && !(choix.equalsIgnoreCase("cli")));{
				System.out.println("Je n'ai pas compris votre réponse");
			}
			
			if(choix.equalsIgnoreCase("com")){
				// passer dans le menu commande
				do {
					System.out.println("\n Créer une commande ? (C), Afficher les commandes (A), Rechercher une commande (R)");
					System.out.println("Quitter (q)");
					
					choix =sc.nextLine();
					
				}while (!(choix.equalsIgnoreCase("C")) && !(choix.equalsIgnoreCase("Q")) && !(choix.equalsIgnoreCase("A")) && !(choix.equalsIgnoreCase("R")));{
					System.out.println("Je n'ai pas compris votre réponse");
				}
					if(choix.equalsIgnoreCase("C")){
					
					}
			}
			else if (choix.equalsIgnoreCase("cli")) {
				//passer dans le menu client
			}
			else if (choix.equalsIgnoreCase("q")){
				pgr=false;
			}
			 
		
		
		
		
//			try {
//				listeClient = clientDao.lister();
//				Iterator<Client> i = listeClient.iterator();
//				
//				while (i.hasNext()) {
//					compteur ++;
//					System.out.println(i.next());
//				}
//				System.out.println(" Il y'a " + compteur + " Clients dans la liste.");
//			}catch (DaoException e) {
//				e.printStackTrace();
//			}
		} 
		System.out.println("\n ===============");
		System.out.println(" == AU REVOIR == ");
		System.out.println(" ===============\n");
	}
}
