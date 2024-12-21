package newstart.service;

import java.util.List;

import newstart.core.Repository;
import newstart.entities.Client;
import newstart.entities.Dette;
import newstart.repository.implementation.RepositoryDette;
import newstart.service.ServiceImplentation.ServiceDette;

public class DetteService implements ServiceDette<Dette> {
    private final Repository<Dette> detteRepository;

    public DetteService(Repository<Dette> detteRepository) {
        this.detteRepository = detteRepository;
    }
    @Override
    public void create(Dette dette) {
        detteRepository.insert(dette);

    }
    @Override
    public boolean saveDetteAvecDetails(Dette dette) throws ClassNotFoundException {
        return ((RepositoryDette) detteRepository).saveDetteAvecDetails(dette);

    }

    @Override
    public List<Dette> show() {
        return detteRepository.findAll();
    }

    @Override
    public List<Dette> showFiltre() {
        return ((RepositoryDette)detteRepository).findFiltre();
    }

    @Override
    public Client getClient(int id) {
        return ((RepositoryDette)detteRepository).getByClient(id);
    }

    @Override
    public List<Dette> getDettesByClientId(int clientId){
        return ((RepositoryDette)detteRepository).getDettesByClientId(clientId);
    }

    @Override
    public List<Dette> getDettesEnCoursByClientId(int clientId){
        return ((RepositoryDette)detteRepository).getDettesEnCoursByClientId(clientId);
    }

    @Override
    public void updateValidation(Dette dette,String etat) {
        ((RepositoryDette)detteRepository).updateValidation(dette.getId(),etat);
    }

    @Override
    public Dette getById(int id) {
        return (detteRepository).getById(id);
    }

    @Override
    public double getMontantTotalClient(int clientId) {
        double total = 0;
        for (Dette dette : getDettesNonSoldeesByClientId(clientId)) {
            total += dette.getMontantTotal()-dette.getMontantVerse();
        }
        return total;
    }

    @Override
    public List<Dette> getDettesNonSoldeesByClientId(int clientId) {
        return ((RepositoryDette)detteRepository).getDettesNonSoldeesByClientId(clientId);
    }

    @Override
    public void update(Dette dette) throws ClassNotFoundException{
        detteRepository.update(dette);
    }
}
