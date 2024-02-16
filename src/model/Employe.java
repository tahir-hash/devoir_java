package model;

public class Employe extends Personne{
    private double salaire;


    public Employe(String nom, String prenom, String adresse, double salaire) {
        super(nom, prenom, adresse);
        this.salaire =salaire;
    }

    public double getSalaire() {
        return salaire;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\''+
                ", salaire='" + salaire + '\''
                ;
    }
}
