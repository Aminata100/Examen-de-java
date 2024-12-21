package newstart.core.factories;

import newstart.repository.implementation.RepositoryArticle;
import newstart.repository.implementation.RepositoryClient;
import newstart.repository.implementation.RepositoryDette;
import newstart.repository.implementation.RepositoryUser;
import newstart.service.ArticleService;
import newstart.service.ClientService;
import newstart.service.DetteService;
import newstart.service.UserService;
import newstart.service.connexion.ConnectionService;

public class FactoryService {
    private ClientService clientService;
    private UserService userService;
    private ConnectionService connectionService;
    private ArticleService articleService;
    private DetteService detteService;
    private final FactoryRepository factoryRepository;

    public FactoryService(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }
    public ClientService getInstanceClientService(){
        RepositoryClient clientRepository = factoryRepository.getInstanceClientRepository();
        if(clientService==null){
            clientService = new ClientService(clientRepository);
        }
        return clientService;
    }

    public UserService getInstanceUserService(){
        RepositoryUser userRepository = factoryRepository.getInstanceUserRepository();
        if(userService==null){
            userService = new UserService(userRepository);
        }
        return userService;
    }

    public ConnectionService getInstanceConnectionService(){
        RepositoryUser userRepository = factoryRepository.getInstanceUserRepository();
        if(connectionService==null){
            connectionService = new ConnectionService(userRepository);
        }
        return connectionService;
    }

    public ArticleService getInstanceArticleService(){
        RepositoryArticle articleRepository = factoryRepository.getInstanceArticleRepository();
        if(articleService==null){
            articleService = new ArticleService(articleRepository);
        }
        return articleService;
    }

    public DetteService getInstanceDetteService(){
        RepositoryDette detteRepository = factoryRepository.getInstanceDetteRepository();
        if(detteService==null){
            detteService = new DetteService(detteRepository);
        }
        return detteService;
    }
}
