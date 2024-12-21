package newstart.repository.implementation;

import java.util.List;

import newstart.core.Repository;
import newstart.entities.Client;
import newstart.entities.Dette;

public interface RepositoryDette extends Repository<Dette>{
    List<Dette> findFiltre();
    Client getByClient(int id);
    List<Dette> getDettesByClientId(int clientId);
    List<Dette> getDettesEnCoursByClientId(int clientId);
    void updateValidation(int id,String etat);
    List<Dette> getDettesNonSoldeesByClientId(int clientId);
    boolean saveDetteAvecDetails(Dette dette)throws ClassNotFoundException;
}

