package com.mysite.sbb.Reply.reply;


import com.mysite.sbb.Article.domain.Article;
import com.mysite.sbb.Reply.dao.ReplyRepository;
import com.mysite.sbb.Reply.domain.Reply;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReplyService {
  private final ReplyRepository replyRepository;

  public void create(Article article, String content) {
    Reply reply = new Reply();
    reply.setContent(content);
    reply.setCreateDate(LocalDateTime.now());
    reply.setArticle(article);
    reply.setReplyLike(false);
    this.replyRepository.save(reply);



}

  public void setLike(Integer replyId) {
    Reply reply = replyRepository.findById(replyId).get();
    if(reply.getReplyLike() == true) {
      reply.setReplyLike(false);
    } else  {
      reply.setReplyLike(true);
    }
    this.replyRepository.save(reply);
  }
}
