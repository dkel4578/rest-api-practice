package com.tj.edu.training.shinsunyoung.controller;

import com.tj.edu.training.shinsunyoung.model.Article;
import com.tj.edu.training.shinsunyoung.model.dto.ArticleListViewResponse;
import com.tj.edu.training.shinsunyoung.model.dto.ArticleResponse;
import com.tj.edu.training.shinsunyoung.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleViewController {

    public final ArticleService articleService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        //1. 첫번째 방법
//        model.addAttribute("articles", articleService.getArticleAll());

        //2. 두번째 방법
        List<ArticleListViewResponse> articleResponseList = articleService.getArticleAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articleResponseList);

        return "articleList";
    }

    @GetMapping("/new-article")
    public String newArticle(Model model){
        return "newArticle";
    }

    @GetMapping("articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = articleService.findById(id);
        model.addAttribute("article", new ArticleListViewResponse(article));

        return "/article";
    }

    @GetMapping("articles/modify/{id}")
    public String updateArticle(@PathVariable Long id, Model model){
        Article article = articleService.findById(id);
        model.addAttribute("article", new ArticleListViewResponse(article));
        return "modifyArticle";
    }
}
