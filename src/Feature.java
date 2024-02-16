import repo.ImplCompteMysql;

import java.util.Scanner;

public class Feature {
    Scanner clavier = new Scanner(System.in);

    private ImplCompteMysql implCompteMysql;
    public Feature(){
        implCompteMysql = new ImplCompteMysql();
    }

    public int menu(){
        int choix;

        System.out.println("1-Ajout d'un compte");
        System.out.println("2-Lister les comptes");
        System.out.println("3-Recherche par numero");
        System.out.println("4-Supprimer");
        System.out.println("5-Mettre à jour");
        System.out.println("6-Quitter");
        do{
            choix = clavier.nextInt();
            if(choix<1 || choix >6){
                System.out.println("Le choix est incorrect .");
            }
        }while(choix<1 || choix >6);
        return  choix;
    }

}
