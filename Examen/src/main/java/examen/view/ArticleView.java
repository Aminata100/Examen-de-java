package newstart.view;

import java.util.List;
import java.util.Scanner;

import newstart.entities.Article;

public class ArticleView {
    private final Scanner scanner;

    public ArticleView() {
        this.scanner = new Scanner(System.in);
    }

    public Article saisie() {
        Article article = new Article();
        System.out.println("Entrer le libelle: ");
        article.setLibelle(scanner.nextLine());
        System.out.println("Entrer la reference: ");
        article.setReference(scanner.nextLine());
        System.out.println("Entrer le prix: ");
        article.setPrix(scanner.nextInt());
        System.out.println("Entrer la quantite en stock: ");
        article.setQteStock(scanner.nextInt());
        scanner.nextLine();
        return article;
    }

    public void affiche(List<Article> articles) {
        if (articles.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else {
            for (Article article : articles) {
                System.out.println(article);
            }
        }
    }
}
