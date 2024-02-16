package repo;

import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonneImplMysql implements IPersonne<Personne> {
    private  Connection connection;
    public PersonneImplMysql() {
        this.connection = new BD().getConnection();
    }
    @Override
    public List<Personne> getAllEmploye() throws SQLException {

        List<Personne> Employes = new ArrayList<>();
        String sql = "SELECT * FROM personne where matricule=null";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result= statement.executeQuery();

        Employe employe= new Employe(
                result.getString("nom"),
                result.getString("prenom"),
                result.getString("adresse"),
                result.getDouble("salaire")
        );
        while(result.next()){
            Employes.add(employe);
        }
        return Employes;
    }

    @Override
    public List<Personne> getAllDirecteur() throws SQLException {
        List<Personne> Directeurs = new ArrayList<>();
        String sql = "SELECT * FROM personne where matricule!=null";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result= statement.executeQuery();

        Directeur directeur= new Directeur(
                result.getString("nom"),
                result.getString("prenom"),
                result.getString("adresse")
        );
        while(result.next()){
            Directeurs.add(directeur);
        }
        return Directeurs;
    }

    @Override
    public void addPersonne(Personne personne, Role type) throws SQLException {
        String sqlDirecteur = "INSERT INTO personne (nom,prenom,adresse,matricule) values (?,?,?,?)";
        String sqlEmploye = "INSERT INTO personne (nom,prenom,adresse,salaire) values (?,?,?,?)";
        String sql = (type==Role.Directeur) ? sqlDirecteur : sqlEmploye;
        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1,personne.getNom());
        statement.setString(2,personne.getPrenom());
        statement.setString(3,personne.getAdresse());
        if (type == Role.Directeur) {
            statement.setString(4, ((Directeur) personne).getMatricule());
        } else {
            statement.setDouble(4, ((Employe) personne).getSalaire());
        }

        statement.executeUpdate();
    }

    @Override
    public void deleteByMatricule(String matricule) throws SQLException {
        try{
            findMatricule(matricule);
            String sql = "Delete from matricule where matricule = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,matricule);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updatePersonne(int id,Personne personne, Role type) throws SQLException {
        String sqlDirecteur = "Update personne SET nom = ? , prenom = ? " +
                ", adresse = ?, matricule = ? where id= ?";
        String sqlEmploye = "Update personne SET nom = ? , prenom = ? " +
                ", adresse = ?, salaire = ? where id= ?";

        String sql = (type==Role.Directeur) ? sqlDirecteur : sqlEmploye;
        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1,personne.getNom());
        statement.setString(2,personne.getPrenom());
        statement.setString(3,personne.getAdresse());
        if (type == Role.Directeur) {
            statement.setString(4, ((Directeur) personne).getMatricule());
        } else {
            statement.setDouble(4, ((Employe) personne).getSalaire());
        }
        statement.setInt(5,id);
        statement.executeUpdate();
    }


    public void findMatricule(String matricule) throws SQLException {
        boolean bool = true;
        String sql = "SELECT * FROM personne where matricule= ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,matricule);
        ResultSet result=  statement.executeQuery();
        if(result.next()){
            return ;
        }
        throw new SQLException("Matricule n'existe pas");

    }

}
