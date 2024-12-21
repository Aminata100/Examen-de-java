package newstart;

import newstart.core.factories.FactoryRepository;
import newstart.core.factories.FactoryService;
import newstart.core.factories.FactoryView;
import newstart.entities.User;
import newstart.service.connexion.ConnectionService;
import newstart.view.MainView.AdminView;
import newstart.view.MainView.BoutiquierView;
import newstart.view.MainView.ClienteleView;
import newstart.view.connexion.ConnectView;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        FactoryRepository factoryRepository = new FactoryRepository();
        FactoryView factoryView = new FactoryView();
        FactoryService factoryService = new FactoryService(factoryRepository);
        ConnectionService connectionService = factoryService.getInstanceConnectionService();
        ConnectView connect = factoryView.getInstanceConnectView();

        System.out.println("=============== Bienvenue ===============");
        
        User userConnected = null;

        while (userConnected == null) {
            System.out.println("Veuillez vous connecter");
            User user = connect.saisie();
            userConnected = connectionService.getUser(user.getLogin(), user.getPassword());
            
            if (userConnected == null || userConnected.getRole() == null) {
                System.out.println("Erreur de connexion. Veuillez réessayer.");
            }
        }

        switch (userConnected.getRole()) {
            case ADMIN -> {
                AdminView adminView = new AdminView();
                System.out.println("=============== Bonjour " + userConnected.getPrenom() + " " + userConnected.getNom() + " ===============");
                adminView.admin();
            }
            case BOUTIQUIER -> {
                BoutiquierView boutiquierView = new BoutiquierView();
                System.out.println("=============== Bonjour " + userConnected.getPrenom() + " " + userConnected.getNom() + " ===============");
                boutiquierView.boutiquier();
            }
            case CLIENT -> {
                ClienteleView clienteleView = new ClienteleView(userConnected);
                System.out.println("=============== Bonjour " + userConnected.getPrenom() + " " + userConnected.getNom() + " ===============");
                clienteleView.clientele();
            }
            default -> System.out.println("Erreur de connexion");
        }
    }
}
