package newstart.core.factories;

import newstart.repository.implementation.RepositoryArticle;
import newstart.repository.implementation.RepositoryClient;
import newstart.repository.implementation.RepositoryDette;
import newstart.repository.implementation.RepositoryUser;
import newstart.repository.repositoryBD.ArticleRepositoryBD;
import newstart.repository.repositoryBD.ClientRepositoryBD;
import newstart.repository.repositoryBD.DetteRepositoryBD;
import newstart.repository.repositoryBD.UserRepositoryBD;


public class FactoryRepository {
    private RepositoryClient clientRepository;
    private RepositoryUser userRepository;
    private RepositoryArticle articleRepository;
    private RepositoryDette detteRepository;

    public RepositoryClient getInstanceClientRepository(){
        userRepository = getInstanceUserRepository();
        if(clientRepository==null){
            clientRepository = new ClientRepositoryBD(userRepository);
        }
        return clientRepository;

    }

    public RepositoryUser getInstanceUserRepository(){
        if(userRepository==null){
            userRepository = new UserRepositoryBD();
        }
        return userRepository;
    }

    public RepositoryArticle getInstanceArticleRepository(){
        if(articleRepository==null){
            articleRepository = new ArticleRepositoryBD();
        }
        return articleRepository;
    }

    public RepositoryDette getInstanceDetteRepository(){
        clientRepository = getInstanceClientRepository();
        if(detteRepository == null){
            detteRepository = new DetteRepositoryBD(clientRepository);
        }
        return detteRepository;
    }


}


