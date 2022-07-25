package com.mysite.sbb.Reply.controller;

import com.mysite.sbb.Article.ArticleForm;
import com.mysite.sbb.Article.domain.Article;
import com.mysite.sbb.Article.service.ArticleService;
import com.mysite.sbb.Reply.ReplyForm;
import com.mysite.sbb.Reply.dao.ReplyRepository;
import com.mysite.sbb.Reply.domain.Reply;
import com.mysite.sbb.Reply.reply.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/reply")
@AllArgsConstructor
public class ReplyController {

  private final ArticleService articleService;
  private final ReplyService replyService;

  @PostMapping("/create/{id}")
  public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid ReplyForm replyForm, BindingResult bindingResult) {
    Article article = this.articleService.getArticle(id);
    if(bindingResult.hasErrors()) {
      model.addAttribute("article", article);
      return "article_detail";
    }

    // 질문만들기
    this.replyService.create(article, replyForm.getContent());
    return String.format("redirect:/article/detail/%s", id);
  }
  @PostMapping("/like/{articleId}/{replyId}")
  public String createReply(@PathVariable("articleId") Integer articleId, @PathVariable("replyId") Integer replyId) {
    this.replyService.setLike(replyId);


    return String.format("redirect:/article/detail/%s", articleId);
  }
}




