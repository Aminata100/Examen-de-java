package newstart.repository.implementation;

import java.util.List;

import newstart.core.Repository;
import newstart.entities.Article;

public interface  RepositoryArticle extends Repository<Article> {
    List<Article> findByDispo();
    Article getByLibelle(String libelle);
}