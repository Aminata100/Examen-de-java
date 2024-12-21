package newstart.repository.implementation;

import newstart.core.Repository;
import newstart.entities.Client;
import newstart.entities.User;

public interface  RepositoryClient extends Repository<Client>{
    Client getByTel(String telephone);
    Client getClientByUser(User user);
}
