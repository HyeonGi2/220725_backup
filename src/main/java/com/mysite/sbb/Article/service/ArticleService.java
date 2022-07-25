package com.mysite.sbb.Article.service;

import com.mysite.sbb.Article.dao.ArticleRepository;
import com.mysite.sbb.Article.domain.Article;
import com.mysite.sbb.util.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  public List<Article> getList() {

    return articleRepository.findAll();
  }

  public Article getArticle(Integer id) {
    Optional<Article> opArticle = this.articleRepository.findById(id);
    if (opArticle.isPresent()) {
      Article article = opArticle.get();
      article.setViewCount(article.getViewCount() + 1);
      this.articleRepository.save(article);
      return article;
    } else {
      throw new DataNotFoundException("article not found");
    }
  }
  public void create(String subject, String content) {
    Article q = new Article();
    q.setSubject(subject);
    q.setContent(content);
    q.setCreateDate(LocalDateTime.now());
    this.articleRepository.save(q);
  }
}





