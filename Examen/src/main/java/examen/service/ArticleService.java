package newstart.service;

import java.util.List;

import newstart.core.Repository;
import newstart.entities.Article;
import newstart.repository.implementation.RepositoryArticle;
import newstart.service.ServiceImplentation.ServiceArticle;


public class ArticleService implements ServiceArticle<Article> {
    private final Repository<Article> articleRepository;

    public ArticleService(Repository<Article> articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void create(Article article) {
        articleRepository.insert(article);
    }

    @Override
    public List<Article> show() {
        return articleRepository.findAll();
    }

    @Override
    public Article getLibelle(String libelle){
        return ((RepositoryArticle)articleRepository).getByLibelle(libelle);
    }


    @Override
    public List<Article> findByDispo() {
        return ((RepositoryArticle)articleRepository).findByDispo();

    }

    @Override
    public void update(Article objet) throws ClassNotFoundException{
        articleRepository.update(objet);
    }

}
