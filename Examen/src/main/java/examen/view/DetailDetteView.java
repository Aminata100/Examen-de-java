package newstart.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import newstart.entities.Article;
import newstart.entities.Client;
import newstart.entities.Detail;
import newstart.entities.Dette;
import newstart.service.ArticleService;
import newstart.service.DetteService;

public class DetailDetteView {
    private final Scanner scanner;
    private final ArticleService articleService;
    private final DetteService detteService;

    public DetailDetteView(ArticleService articleService, DetteService detteService) {
        this.scanner = new Scanner(System.in);
        this.articleService = articleService;
        this.detteService = detteService;
    }

    public Dette saisie(Client client) {
        Dette dette = new Dette();
        dette.setClient(client);
        dette.setSoldation(false); // Par défaut, la dette n'est pas soldée
        dette.setValidation("En cours");
        dette.onPrePersist();
        dette.onPreUpdate();
    
        List<Detail> details = new ArrayList<>();
        String res;
    
        do {
            System.out.println("Voulez-vous ajouter un article à la dette (o/n) ?");
            res = scanner.nextLine();
    
            if ("o".equalsIgnoreCase(res)) {
                Detail detail = new Detail();
    
                System.out.println("Entrez le libellé de l'article :");
                String libelle = scanner.nextLine();
                Article article = articleService.getLibelle(libelle);
    
                if (article != null) {
                    detail.setArticle(article);
    
                    System.out.println("Entrez la quantité vendue :");
                    int qteVendu = scanner.nextInt();
                    scanner.nextLine(); // Consomme la nouvelle ligne après la saisie d'un entier
                    detail.setQteVendu(qteVendu);
                    detail.setPrixVente(article.getPrix() * qteVendu);
                    detail.onPrePersist();
                    detail.onPreUpdate();
                    details.add(detail);
    
                    // Ajouter le montant au total de la dette
                    double montantTotal = dette.getMontantTotal();
                    montantTotal += detail.getPrixVente();
                    dette.setMontantTotal(montantTotal);
                } else {
                    System.out.println("Article non trouvé.");
                }
            } else if (!"n".equalsIgnoreCase(res)) {
                System.out.println("Réponse invalide. Veuillez entrer 'o' pour oui ou 'n' pour non.");
            }
    
        } while (!"n".equalsIgnoreCase(res));
    
        dette.setDetails(details);
    
        return dette;
    }
    


    public void affiche(List<Dette> dettes) {
        if (dettes.isEmpty()) {
            System.out.println("Aucune dette trouvée.");
        } else {
            for (Dette dette : dettes) {
                System.out.println(dette);
            }
        }
    }

    public Dette enregistrePayment(Client client) {
        List<Dette> dettes = detteService.getDettesByClientId(client.getId());
        if (dettes.size() == 1) {
            Dette dette = dettes.get(0);
            System.out.println("Client a une seule dette avec un montant total de " + dette.getMontantRestant());
            return traiterPaiement(dette);
        } else if (dettes.size() > 1) {
            System.out.println("Le client a plusieurs dettes, veuillez sélectionner la dette à payer :");

            for (int i = 0; i < dettes.size(); i++) {
                Dette dette = dettes.get(i);
                System.out.println((i + 1) + ". Montant total de la dette : " + (dette.getMontantVerse() + dette.getMontantRestant()) +
                        " | Montant restant à payer : " + dette.getMontantRestant());
            }

            int choixDette;
            while (true) {
                System.out.println("Entrez le numéro de la dette que vous souhaitez payer :");
                choixDette = scanner.nextInt();
                scanner.nextLine();
                if (choixDette >= 1 && choixDette <= dettes.size()) {
                    break;
                }
                System.out.println("Numéro invalide. Veuillez réessayer.");
            }
            Dette detteSelectionnee = dettes.get(choixDette - 1);
            return traiterPaiement(detteSelectionnee);
        }
        return null;
    }

    // Méthode pour traiter le paiement d'une dette
    private Dette traiterPaiement(Dette dette) {
        boolean montantValide = false;
        double montantPaye = 0;

        while (!montantValide) {
            System.out.println("Entrez le montant que le client souhaite payer :");
            montantPaye = scanner.nextDouble();
            scanner.nextLine(); // Consomme la nouvelle ligne après la saisie

            if (montantPaye <= 0) {
                System.out.println("Le montant payé doit être positif. Veuillez réessayer.");
            } else if (montantPaye > dette.getMontantRestant()) {
                System.out.println("Le montant payé dépasse le montant restant à payer. Veuillez réessayer.");
            } else {
                montantValide = true; // Le montant est valide, on sort de la boucle
            }
        }

        // Mise à jour du montant versé et restant
        double montantVerse = dette.getMontantVerse();
        montantVerse += montantPaye;
        dette.setMontantVerse(montantVerse);

        if (dette.getMontantRestant() <= 0) {
            dette.setSoldation(true);
            System.out.println("La dette est maintenant soldée.");
        } else {
            System.out.println("Il reste encore " + dette.getMontantRestant() + " à payer.");
        }
        dette.onPreUpdate();
        return dette;
    }

    // fonction qui donne le choix en  Validee et Refusee la dette en cours et retourne le resultat
    public String traiterValidation() {
        int res;
        do {
            System.out.println("Veuillez choisir ");
            System.out.println("1- Validee");
            System.out.println("2- Refusee");
            res = scanner.nextInt();
        } while (res != 1 && res != 2);
        return res == 1 ? "Validee" : "Refusee";
    }
}
