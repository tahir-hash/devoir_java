package model;
import java.sql.SQLException;
import java.util.List;
public interface IPersonne<T>{
    List<T> getAllEmploye() throws SQLException;
    List<T> getAllDirecteur() throws SQLException;

    void  addPersonne(T t, Role type) throws SQLException;
    void deleteByMatricule(String matricule) throws SQLException;

    void updatePersonne(int id,T t, Role type) throws SQLException;



}
