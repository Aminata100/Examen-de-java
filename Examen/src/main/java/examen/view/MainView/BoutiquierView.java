package newstart.view.MainView;

import java.lang.reflect.Field;
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
import newstart.view.ClientView;
import newstart.view.DetailDetteView;
import newstart.view.UserView;


public class BoutiquierView {
    private  final Scanner scanner = new Scanner(System.in);

    public  void boutiquier() throws ClassNotFoundException {
        FactoryRepository factoryRepository = new FactoryRepository();
        FactoryView factoryView = new FactoryView();
        FactoryService factoryService = new FactoryService(factoryRepository);
        ClientService clientService = factoryService.getInstanceClientService();
        DetteService detteService= factoryService.getInstanceDetteService();
        ArticleService articleService = factoryService.getInstanceArticleService();
        ClientView clientView = factoryView.getInstanceClientView(detteService);
        DetailDetteView detteView = factoryView.getInstanceDetteView(articleService,detteService);
        UserView userView = factoryView.getInstanceUserView();



    int choix;
        do {
            choix = menu();
            scanner.nextLine();
            switch (choix) {
                case 1 -> {
                    Client client = new Client();
                    do {
                        client = clientView.saisie();
                    } while (clientService.getTel(client.getTelephone()) != null);
                    System.out.println("Voulez-vous créer un compte à ce client ? (O/N): ");
                    String res = scanner.next();
                    if(res.equalsIgnoreCase("O")) {
                        User user;
                        user = userView.saisieClientCompte(client);
                        client.setCompte(user);
                    }
                    clientService.create(client);
                }
                case 2 -> clientView.affiche(clientService.show());
                case 3 -> {
                    System.out.println("Entrez le numéro de téléphone à rechercher:");
                    String tel = scanner.nextLine();
                    Client client = clientService.getTel(tel);
                    if (client != null) {
                        System.out.println(client);
                        
                        // Utilisation de la réflexion pour afficher les champs
                        Field[] fields = client.getClass().getDeclaredFields();
                        int i=0;
                        for (Field field : fields) {
                            i=i+1;
                            field.setAccessible(true);  // Permet d'accéder aux champs privés
                            try {
                                // Affichage du nom du champ et de sa valeur
                                System.out.println(field.getName() + i + ": " + field.get(client));
                            } catch (IllegalAccessException e) {
                                System.out.println("Impossible de lire la valeur du champ " + field.getName());
                            }
                        }
                        
                        System.out.println(client);
                    } else {
                        System.out.println("Client non trouvé.");
                    }
                }
                case 4 -> {
                    Dette dette = new Dette();
                    System.out.println("Entrez le numéro de téléphone :");
                    String tel = scanner.nextLine();
                    Client client = clientService.getTel(tel);
                    if (client != null) {
                        dette = detteView.saisie(client);
                        dette.setValidation("Validée");
                    }else {
                        System.out.println("Client non trouvé.");
                    }
                    // Enregistrez la dette et ses détails dans la base de données
                    if (detteService.saveDetteAvecDetails(dette)) {
                        System.out.println("La dette a été enregistrée avec succès !");
                    } else {
                        System.out.println("Une erreur s'est produite lors de l'enregistrement de la dette.");
                    }
                }
                case 5 ->{
                    System.out.println("Entrez le numéro de téléphone à rechercher:");
                    String tel = scanner.nextLine();
                    Client client = clientService.getTel(tel);
                    if (client != null) {
                        detteService.update(detteView.enregistrePayment(client));
                    } else {
                        System.out.println("Client non trouvé.");
                    }
                }
                case 6 -> {
                    detteView.affiche(detteService.showFiltre());
                }
                case 7 -> {
                        detteView.affiche(detteService.getDettesEnCoursByClientId(0));
                }
                case 8 -> {
                    System.out.println("Entrez le numéro de téléphone :");
                    String tel = scanner.nextLine();
                    Client client = clientService.getTel(tel);
                    if (client != null) {
                        detteView.affiche(detteService.getDettesByClientId(client.getId()));
                        scanner.nextLine();
                        System.out.println("Veuillez entrer le numéro de la demande de dette à valider ou refuser :");
                        int num = scanner.nextInt();
                        Dette dette = detteService.getById(num);
                        if (dette != null) {
                            dette.setValidation(detteView.traiterValidation());
                            detteService.update(dette);
                        }
                    } else {
                        System.out.println("Client non trouvé.");
                    }
                }
                case 9 -> System.out.println("Au revoir!");
                default -> System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 9);
    }
    
    public int menu() {
        System.out.println("\n1- Créer un client");
        System.out.println("2- Lister les clients");
        System.out.println("3- Rechercher un client");
        System.out.println("4- Creer une dette");
        System.out.println("5- Enregistrer un paiement");
        System.out.println("6- Lister les dettes non soldées");
        System.out.println("7- Lister les demandes de dette en cours");
        System.out.println("8- Valider ou Refuser une demande de dette");
        System.out.println("9- Quitter");
        System.out.print("Faites un choix: ");
        return scanner.nextInt();
    }

}
