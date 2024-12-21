package newstart.view.MainView;

import java.util.Scanner;

import newstart.core.factories.FactoryRepository;
import newstart.core.factories.FactoryService;
import newstart.core.factories.FactoryView;
import newstart.entities.Client;
import newstart.entities.Dette;
import newstart.entities.User;
import newstart.service.ArticleService;
import newstart.service.ClientService;
import newstart.service.DetteService;
import newstart.view.DetailDetteView;

public class ClienteleView {
    private  final Scanner scanner = new Scanner(System.in);
    User user;

    public ClienteleView(User user) {
        this.user = user;
    }

    public  void clientele() throws ClassNotFoundException {
        FactoryRepository factoryRepository = new FactoryRepository();
        FactoryView factoryView = new FactoryView();
        FactoryService factoryService = new FactoryService(factoryRepository);
        ArticleService articleService = factoryService.getInstanceArticleService();
        DetteService detteService= factoryService.getInstanceDetteService();
        ClientService clientService = factoryService.getInstanceClientService();
        DetailDetteView detteView = factoryView.getInstanceDetteView(articleService,detteService);

        Client client = clientService.getByUser(user);

        int choix;
        do {
            choix = menu();
            scanner.nextLine();
            switch (choix) {
                case 1 -> {
                        detteView.affiche(detteService.getDettesByClientId(client.getId()));
                }
                case 2 -> {
                    Dette dette = detteView.saisie(client);
                    detteService.create(dette);
                }
                case 3 -> {
                    detteView.affiche(detteService.getDettesEnCoursByClientId(client.getId()));
                }
                case 4 -> {
                    System.out.println("Veuillez entrer le numéro de la demande de dette à valider ou refuser :");
                    int num = scanner.nextInt();
                    Dette dette = detteService.getById(num);
                    if (dette != null) {
                        dette.setValidation("En cours");
                        detteService.update(dette);
                    }
                }
                case 5 -> System.out.println("Bye Bye");
                default -> System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 5);
    }
    
    public int menu() {
        System.out.println("1- Lister les dettes non soldées");
        System.out.println("2- Faire une demande de dette");
        System.out.println("3- Lister les demandes de dette");
        System.out.println("4- Envoyer une relance");
        System.out.println("5- Quitter");
        System.out.print("Faites un choix: ");
        return scanner.nextInt();
    }

}

