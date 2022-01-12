package crm.launcher;
import java.util.Scanner;
public class Authentication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		int nbrDeTentative=0;

		do {
			System.out.println("Entrez votre identifiant :");
			Scanner sc = new Scanner(System.in);
			String reponse1 = sc.nextLine();


			if (true /*reponse1==retour de la bas de donnée après verif */) {
				System.out.println("Entrez votre mot de passe :");
				String reponse2 = sc.nextLine();
				if (true /*reponse2 == retour de la base de donnée après verif*/)	{
					// continuer le programme;
				}
				else {
					nbrDeTentative ++;
					break;
				}


			}
			else {
				nbrDeTentative ++;
				break;
			}



		} while ( nbrDeTentative<5);

	}

}
