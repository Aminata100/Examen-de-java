package newstart.service.connexion;

import newstart.core.Repository;
import newstart.entities.User;
import newstart.repository.implementation.RepositoryUser;

public class ConnectionService {
    private final Repository<User> userRepository;

    public ConnectionService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }
    
    public User getUser(String login,String password){
        return ((RepositoryUser) userRepository).getByLoginAndPassword(login,password);
    }
}
