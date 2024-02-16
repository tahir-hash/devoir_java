import model.*;
import repo.PersonneImplMysql;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Feature {
    Scanner clavier = new Scanner(System.in);

    private PersonneImplMysql implCompteMysql;
    public Feature(){
        implCompteMysql = new PersonneImplMysql();
    }

    public int menu(){
        int choix;

        System.out.println("1-Ajout d'un directeur");
        System.out.println("2-Ajout d'un employe");
        System.out.println("3-Liste directeur");
        System.out.println("4-Liste Employe");
        System.out.println("5-Modifier une personne");
        System.out.println("6-Delete");
        System.out.println("7-Quit");

        do{
            choix = clavier.nextInt();
            if(choix<1 || choix >7){
                System.out.println("Le choix est incorrect .");
            }
        }while(choix<1 || choix >7);
        return  choix;
    }


    public void listDirecteur() throws SQLException {
        List<Personne> personnes =  implCompteMysql.getAllDirecteur();
        for (Personne p: personnes) {
            System.out.println(p.toString());
        }
    }

    public void listEmploye() throws SQLException {
        List<Personne> personnes =  implCompteMysql.getAllEmploye();
        for (Personne p: personnes) {
            System.out.println(p.toString());
        }
    }


    public void ajoutPersonne(Role type) throws SQLException {
        Personne personne;
        clavier.nextLine();
        System.out.println("Saisir le Nom");
        String nom = clavier.nextLine();
        System.out.println("Saisir le Prenom");
        String prenom = clavier.nextLine();
        System.out.println("Saisir l'adresse'");
        String adresse = String.valueOf(AdresseEnum.valueOf(clavier.nextLine()));
        clavier.nextLine();
        if( type ==Role.Directeur){
            personne = new Directeur(nom,prenom,adresse);
        }else {
            System.out.println("Saisir le salaire");
            personne = new Employe(nom,prenom,adresse,clavier.nextDouble());

        }
        implCompteMysql.addPersonne(personne, type);
    }
    public void delete() throws SQLException {
        String matricule;
        System.out.println("Entrez le matricule");
        matricule = clavier.nextLine();
        implCompteMysql.deleteByMatricule(matricule);
    }


    public void update(Role type) throws SQLException {
        Personne personne;
        clavier.nextLine();
        System.out.println("Saisir l'id");
        int id = clavier.nextInt();
        System.out.println("Saisir le Nom");
        String nom = clavier.nextLine();
        System.out.println("Saisir le Prenom");
        String prenom = clavier.nextLine();
        System.out.println("Saisir l'adresse'");
        String adresse = String.valueOf(AdresseEnum.valueOf(clavier.nextLine()));
        clavier.nextLine();
        if( type ==Role.Directeur){
            personne = new Directeur(nom,prenom,adresse);
        }else {
            System.out.println("Saisir le salaire");
            personne = new Employe(nom,prenom,adresse,clavier.nextDouble());

        }
        implCompteMysql.updatePersonne(id,personne, type);
    }
}
