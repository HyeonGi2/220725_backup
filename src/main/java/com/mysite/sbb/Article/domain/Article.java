package com.mysite.sbb.Article.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mysite.sbb.Reply.domain.Reply;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 200)
  private String subject;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;

  private int viewCount;

  @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
  private List<Reply> replyList;

}
