package crm.launcher;


import java.util.Iterator;
import java.util.List;

import crm.dao.ClientDao;
import crm.dao.CommandeDao;
//import crm.dao.CommandeDaoImpl;
import crm.dao.DaoException;
import crm.dao.DaoFactory;
//import crm.dao.ClienDaoImpl;
import crm.model.Client;
//import crm.model;
import crm.model.Commande;
import crm.model.Statut;
import crm.model.TypeCommande;



public class LauncherTestCommande {

	public static void main(String[] args) {

		CommandeDao commandeDao = DaoFactory.getInstance().getCommandeDao();
		ClientDao clientDao = DaoFactory.getInstance().getClientDao();
		List<Commande> listCommandes;
		Commande tmp;

		try {
			listCommandes = commandeDao.lister();

		    Iterator<Commande> i = listCommandes.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			e.printStackTrace();
		}



		try {

			Client c = clientDao.trouver(1);
			commandeDao.creer(new Commande("Zweig", 55f,10f,25f,Statut.EN_COURS,TypeCommande.OLA, "LOlol", c ));

			listCommandes = commandeDao.lister();

		    Iterator<Commande> i = listCommandes.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			tmp = commandeDao.trouver(1);
			System.out.println(tmp);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		try {

			Long idToUpdate= 1L;
			tmp = commandeDao.trouver(idToUpdate);
			System.out.println(tmp);

			tmp.setLabel("RIGOLO");
			tmp.setTVA(55f);

			commandeDao.modifier(tmp);

			listCommandes = commandeDao.lister();

		    Iterator<Commande> i = listCommandes.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		    } catch (DaoException e) {
				e.printStackTrace();
			}


		}




}
