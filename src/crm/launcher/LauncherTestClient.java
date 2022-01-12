package crm.launcher;


import java.util.Iterator;
import java.util.List;

import crm.dao.ClientDao;
//import crm.dao.ClientDaoImpl;
import crm.dao.DaoException;
import crm.model.Client;
import crm.model.Statut;
import crm.dao.DaoFactory;
import crm.dao.ClientDao;
//import crm.dao.ClienDaoImpl;
import crm.model.Client;
//import crm.model;



public class LauncherTestClient {

	public static void main(String[] args) {

		ClientDao clientDao = DaoFactory.getInstance().getClientDao();
		List<Client> listClients;
		Client tmp;
		
		
		// Lister
		/*
		try {
			listClients = clientDao.lister();
			
		    Iterator<Client> i = listClients.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			e.printStackTrace();
		}
		*/
		
		// Créer
		
	
		try {
			
			//Client c = clientDao.trouver(1);
			clientDao.creer(new Client("Zweig", "POLL","RENAULT","elolllooj@yyy.net","055161989",true ));
			
			listClients = clientDao.lister();
			
		    Iterator<Client> i = listClients.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Trouver by Id
		
		/*
		try {
			tmp = clientDao.trouver(1);
			System.out.println(tmp);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
		//Trouver by nom
		
		
		try {
			tmp = clientDao.trouver("Rii");
			System.out.println(tmp);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Modifier
		
		/*
		try {
			
			Long idToUpdate= 1L;
			tmp = clientDao.trouver(idToUpdate);
			System.out.println(tmp);
			
			tmp.setNom("Rii");
			tmp.setEmail("jjjj@eyhyhe.fr");
			
			clientDao.modifier(tmp);
			
			listClients = clientDao.lister();
			
		    Iterator<Client> i = listClients.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		    } catch (DaoException e) {
				e.printStackTrace();
			}
			*/
		
		}
		
	

	

}
