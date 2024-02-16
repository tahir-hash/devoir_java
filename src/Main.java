import model.BD;
import model.Directeur;
import model.Personne;
import model.Role;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
         int choix;

        Feature feature = new Feature();

        do{
            choix = feature.menu();

            switch (choix){

                case  1:
                    feature.ajoutPersonne(Role.Directeur);
                    break;
                case  2:
                    feature.ajoutPersonne(Role.Employe);
                    break;
                case  3:
                    feature.listDirecteur();
                    break;
                case  4:
                    feature.listEmploye();
                    break;
                case  5:
                    feature.update(Role.Directeur);
                    break;
                case  6:
                    feature.delete();
                    break;
                default:
                    System.out.println("quit");

            }
        }while(choix != 6);
    }
}