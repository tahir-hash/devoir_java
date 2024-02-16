package model;

public class Directeur extends Personne{

    private String matricule ;


    public Directeur(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
        this.matricule = this.genererMatricule();
    }
    // Generer matricule
    public String genererMatricule() {
        String matricule =this.prenom.charAt(0)+"000"+this.nom.length();
        return matricule.toUpperCase();
    }

    public String getMatricule() {
        return matricule;
    }
    @Override
    public String toString() {
        return "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\''+
                ", matricule='" + matricule + '\''
                ;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
