package newstart.core.factories;

import newstart.view.ArticleView;
import newstart.view.ClientView;
import newstart.view.DetailDetteView;
import newstart.view.UserView;
import newstart.view.connexion.ConnectView;
import newstart.service.ArticleService;
import newstart.service.DetteService;


public class FactoryView {
    private ClientView clientView;
    private UserView userView;
    private ConnectView connectView;
    private ArticleView articleView;
    private DetailDetteView detteView;
    
    public ClientView getInstanceClientView(DetteService detteService){
        if(clientView==null){
            clientView = new ClientView(detteService);
        }
        return clientView;
    }

    public UserView getInstanceUserView(){
        if(userView==null){
            userView = new UserView();
        }
        return userView;
    }

    public ConnectView getInstanceConnectView(){
        if(connectView==null){
            connectView = new ConnectView();
        }
        return connectView;
    }

    public ArticleView getInstanceArticleView(){
        if(articleView==null){
            articleView = new ArticleView();
        }
        return articleView;
    }
    

    public DetailDetteView getInstanceDetteView(ArticleService articleService, DetteService detteService){
        if(detteView==null){
            detteView = new DetailDetteView(articleService,detteService);
        }
        return detteView;
    }
}
