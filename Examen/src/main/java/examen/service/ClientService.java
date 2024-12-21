package newstart.service;

import java.util.List;

import newstart.core.Repository;
import newstart.entities.Client;
import newstart.entities.User;
import newstart.repository.implementation.RepositoryClient;
import newstart.service.ServiceImplentation.ServiceClient;

public class ClientService implements ServiceClient<Client>{
    private final Repository<Client> clientRepository;

    public ClientService(Repository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    @Override
    public void create(Client client) {
        clientRepository.insert(client);

    }
    @Override
    public List<Client> show() {
        return clientRepository.findAll();
    }

    @Override
    public Client getTel(String telephone){
        return ((RepositoryClient) clientRepository).getByTel(telephone);
    }

    public Client getByUser(User user) {
        return ((RepositoryClient) clientRepository).getClientByUser(user);
    }

    public void update (Client client) throws ClassNotFoundException{
        clientRepository.update(client);
    }
}
