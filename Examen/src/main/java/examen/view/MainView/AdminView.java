package newstart.view.MainView;

import java.util.Scanner;

import newstart.core.factories.FactoryRepository;
import newstart.core.factories.FactoryService;
import newstart.core.factories.FactoryView;
import newstart.entities.Article;
import newstart.entities.Client;
import newstart.entities.User;
import newstart.enums.Role;
import newstart.service.ArticleService;
import newstart.service.ClientService;
import newstart.service.UserService;
import newstart.view.ArticleView;
import newstart.view.UserView;

public class AdminView {
    private  final Scanner scanner = new Scanner(System.in);

    public void admin() throws ClassNotFoundException {
        FactoryRepository factoryRepository = new FactoryRepository();
        FactoryView factoryView = new FactoryView();
        FactoryService factoryService = new FactoryService(factoryRepository);
        ClientService clientService = factoryService.getInstanceClientService();
        UserService userService= factoryService.getInstanceUserService();
        ArticleService articleService = factoryService.getInstanceArticleService();
        UserView userView = factoryView.getInstanceUserView();
        ArticleView articleView = factoryView.getInstanceArticleView();
    
    
        int choix;
        do {
            choix = menu();
            scanner.nextLine();
            switch (choix) {
                case 1 -> {
                    User user = new User();
                    do {
                        System.out.println("Entrez le numéro de téléphone à rechercher:");
                        String tel = scanner.nextLine();
                        Client client = clientService.getTel(tel);
                        if (client != null) {
                            user = userView.saisieClientCompte(client);
                            client.setCompte(user);
                            clientService.update(client);
                        } else {
                            System.out.println("Client non trouvé.");
                        }
                    } while (userService.getLogin(user.getLogin()) != null);
                    userService.create(user);
                }
                case 2 -> {
                    User user = new User();
                    do {
                        user = userView.saisie();
                    } while (userService.getLogin(user.getLogin()) != null);
                    userService.create(user);
                }
                case 3 -> {
                    System.out.println("Entrez le login: ");
                    String nom = scanner.nextLine();
                    User user = userService.getLogin(nom);
                    if (user != null) {
                        if (user.getStatut().equalsIgnoreCase("Desactiver")) {
                            user.setStatut("Activer");
                        } else if (user.getStatut().equalsIgnoreCase("Activer")) {
                            user.setStatut("Desactiver");
                        }
                        userService.update(user);
                        System.out.println("Opération réussie");
                    } else {
                        System.out.println("Utilisateur introuvable");
                    }
                }
                case 4 -> {
                    Role role = userView.saisieRole();
                    userView.affiche(userService.showRole(role));
                }
                case 5 -> {
                    String res;
                    do {
                        System.out.println("Voulez-vous ajouter un article (o/n) ?");
                        res = scanner.nextLine();
                        if ("o".equalsIgnoreCase(res)) {
                            Article article;
                            do {
                                article = articleView.saisie();
                                if (articleService.getLibelle(article.getLibelle()) != null) {
                                    System.out.println("Cet article existe déjà. Veuillez saisir un autre article.");
                                }
                            } while (articleService.getLibelle(article.getLibelle()) != null);
                            articleService.create(article);
                            System.out.println("Article ajouté avec succès.");
                        } else if (!"n".equalsIgnoreCase(res)) {
                            System.out.println("Réponse invalide. Veuillez entrer 'o' pour oui ou 'n' pour non.");
                        }
                    } while (!"n".equalsIgnoreCase(res));
                }
                case 6 -> {
                    articleView.affiche(articleService.show());
                }
                case 7 -> {
                    articleView.affiche(articleService.findByDispo());
                }
                case 8 -> {
                    Article article;
                    System.out.println("Entrez le libellé de l'article: ");
                    String lib = scanner.nextLine();
                    article = articleService.getLibelle(lib);
                    if (article != null) {
                        System.out.println("Entrez la nouvelle quantité de l'article: ");
                        int newQty = scanner.nextInt();
                        article.setQteStock(newQty);
                        articleService.update(article);
                        System.out.println("Quantité mise à jour.");
                    } else {
                        System.out.println("Article non trouvé.");
                    }
                }
                case 9 -> {
                    System.out.println("Pas disponible");
                }
                case 10 -> {
                    System.out.println("Au revoir!");
                }
                default -> System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 10);
        
    }
    
        public int menu() {
            System.out.println("\n1- Créer un compte utilisateur");
            System.out.println("2- Créer un compte utilisateur avec role ");
            System.out.println("3- Desactiver/Activer un compte utilisateur");
            System.out.println("4- Lister les comptes Actifs par role");
            System.out.println("5- Creer des articles");
            System.out.println("6- Lister tous les articles");
            System.out.println("7- Lister les articles disponibles");
            System.out.println("8- Mettre à jour la quantité d'un article");
            System.out.println("9- Archiver les dettes soldees");
            System.out.println("10- Quitter");
            System.out.print("Faites un choix: ");
            return scanner.nextInt();
        }
    
    }
    
