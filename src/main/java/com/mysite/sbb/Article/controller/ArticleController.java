package com.mysite.sbb.Article.controller;

import com.mysite.sbb.Article.ArticleForm;
import com.mysite.sbb.Article.domain.Article;
import com.mysite.sbb.Article.service.ArticleService;
import com.mysite.sbb.Reply.ReplyForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/article")

@AllArgsConstructor

public class ArticleController {

  @Autowired
  private ArticleService articleService;


  @RequestMapping("/list")
  public String showArticles(Model model) {
    List<Article> articleList = articleService.getList();

    model.addAttribute("articleList", articleList);

    return "article_list";

  }

  @RequestMapping("/detail/{id}")
  public String detail(Model model, @PathVariable("id") Integer id, ReplyForm replyForm) {
    Article article = this.articleService.getArticle(id);
    model.addAttribute("article", article);

    return "article_detail";

  }

  @GetMapping("/create")
  public String articleCreate(ArticleForm articleForm) {
    return "article_form";
  }

  @PostMapping("/create")
  public String articleCreate(@Valid ArticleForm articleForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "article_form";
    }
    this.articleService.create(articleForm.getSubject(), articleForm.getContent());
    return "redirect:/article/list";
  }
}
