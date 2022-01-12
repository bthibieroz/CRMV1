package crm.launcher;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import crm.dao.ClientDao;
import crm.dao.DaoFactory;
import crm.dao.UtilisateurDao;
import crm.model.Client;
import crm.model.Commande;
import crm.model.Statut;
import crm.model.TypeCommande;
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
		boolean pgr;
		boolean menu;
		Scanner sc = new Scanner(System.in);
		String choix;
		
		System.out.println(" ===============");
		System.out.println(" == BIENVENUE == ");
		System.out.println(" ===============\n");
		
		while(pgr) {
			menu=true;
			while(menu) {			
				do {
				
					System.out.println(" Que souhaitez vous faire ?\n");
					System.out.println(" Aller dans le menu COMMANDES, entrez 'COM'");
					System.out.println(" Aller dans le menu CLIENTS, entrez 'CLI'");
					System.out.println(" Quittez ? entrez 'q'\n");
					
					choix = sc.nextLine();
					
				}while(!(choix.equalsIgnoreCase("q")) && !(choix.equalsIgnoreCase("com")) && !(choix.equalsIgnoreCase("cli")));{
					System.out.println("Je n'ai pas compris votre réponse\n");
				}
				
				// passer dans le menu commande
				if(choix.equalsIgnoreCase("com")){
					do {
						System.out.println("\n Créer une commande ? (C), Afficher les commandes (A), Rechercher une commande (R)");
						System.out.println("Menu principal (menu) / Quitter (q)");
						
						choix =sc.nextLine();
						
					}while (!(choix.equalsIgnoreCase("menu")) && !(choix.equalsIgnoreCase("C")) && !(choix.equalsIgnoreCase("Q")) && !(choix.equalsIgnoreCase("A")) && !(choix.equalsIgnoreCase("R")));{
						System.out.println("Je n'ai pas compris votre réponse");
					}
					if(choix.equalsIgnoreCase("C")){
						try {
							String label;
							float tjmHT;
							float dureeJours;	
							float TVA;
							Client client;
							
							System.out.println("\n Saisir le label de la commande :");
							label = sc.nextLine();
							System.out.println("\n Saisir le taux journalier moyen :");
							tjmHT = Float.parseFloat(sc.nextLine());
							System.out.println("\n Saisir la nombre de jours :");
							dureeJours = Float.parseFloat(sc.nextLine());
							System.out.println("\n Saisir la TVA :");
							TVA = Float.parseFloat(sc.nextLine());
							System.out.println("\n saisir l' ID d'un client :");
							client = clientDao.trouver(Long.parseLong(sc.nextLine()));
							
							commandeDao.creer(new Commande(label,tjmHT,dureeJours,TVA,Statut.NOUVELLE,TypeCommande.EN_LIGNE,client));
							
							System.out.println("\n Votre commande a bien été créée.");
							
						} catch(DaoException e) {
							e.printStackTrace();
						}
					}
					else if (choix.equalsIgnoreCase("A")) {
						try{
							listeCommande = commandeDao.lister();
						Iterator<Commande> i = listeCommande.iterator();
						
						while (i.hasNext()) {
							compteur ++;
							System.out.println(i.next());
						}
						System.out.println(" Il y'a " + compteur + " Commandes dans la liste.");
						
						}catch(DaoException e) {
							e.printStackTrace();
						}
					}
					else if(choix.equalsIgnoreCase("R")) {
						try{
						System.out.println("\n Saisir l'ID de la commande :");
						Long id = Long.parseLong(sc.nextLine());
						Commande com = commandeDao.trouver(id);
						System.out.println(com);
						} catch (DaoException e) {
							e.printStackTrace();
						}
					}
					else if(choix.equalsIgnoreCase("menu")) {
						menu=false;
					}
					else if(choix.equalsIgnoreCase("q")) {
						pgr=false;
					}
				}
				
				
				//passer dans le menu client
				else if (choix.equalsIgnoreCase("cli")) {
					do {
						System.out.println("\n Créer un client ? (C), Afficher les clients (A), Rechercher un client (R)");
						System.out.println("Menu principal (menu) / Quitter (q)");
						
						choix =sc.nextLine();
						
					}while (!(choix.equalsIgnoreCase("menu")) && !(choix.equalsIgnoreCase("C")) && !(choix.equalsIgnoreCase("Q")) && !(choix.equalsIgnoreCase("A")) && !(choix.equalsIgnoreCase("R")));{
						System.out.println("Je n'ai pas compris votre réponse");
					}
					if (choix.equalsIgnoreCase("C")) {
						try {
							String nom;
							String prenom;
							String entreprise;	
							String email;
							String telephone;
							
							System.out.println("\n Saisir un nom:");
							nom = sc.nextLine();
							System.out.println("\n Saisir un prenom:");
							prenom = sc.nextLine();
							System.out.println("\n Saisir l'entreprise du client :");
							entreprise = sc.nextLine();
							System.out.println("\n Saisir un email :");
							email = sc.nextLine();
							System.out.println("\n saisir un numéro de téléphone :");
							telephone = sc.nextLine();
							
							clientDao.creer(new Client(nom,prenom,entreprise,email,telephone));
							
							System.out.println("\n Votre commande a bien été créée.");
							
						} catch(DaoException e) {
							e.printStackTrace();
						}
					}
					
					
					else if (choix.equalsIgnoreCase("A")) {
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
					
					else if(choix.equalsIgnoreCase("R")) {
						try{
						System.out.println("\n Saisir l'ID du client :");
						Long id = Long.parseLong(sc.nextLine());
						Client cli = clientDao.trouver(id);
						System.out.println(cli);
						} catch (DaoException e) {
							e.printStackTrace();
						}
					}	
					
					else if(choix.equalsIgnoreCase("menu")) {
						menu=false;
					}
				
					else if(choix.equalsIgnoreCase("q")) {
						pgr=false;
					}
					
				}
					
				else if (choix.equalsIgnoreCase("q")){
					pgr=false;
				}
			}
		} 
		
		System.out.println("\n ===============");
		System.out.println(" == AU REVOIR == ");
		System.out.println(" ===============\n");
	}
}
