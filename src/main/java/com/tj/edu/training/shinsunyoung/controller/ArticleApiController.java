package com.tj.edu.training.shinsunyoung.controller;

import com.tj.edu.training.shinsunyoung.model.Article;
import com.tj.edu.training.shinsunyoung.model.dto.AddArticleRequest;
import com.tj.edu.training.shinsunyoung.model.dto.ArticleResponse;
import com.tj.edu.training.shinsunyoung.model.dto.UpdateArticleRequest;
import com.tj.edu.training.shinsunyoung.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = articleService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<Article> articleList = articleService.getArticleAll();

        List<ArticleResponse> articleResponseList = articleList.stream()
                .map(ArticleResponse::new)
                .toList();
//        List<ArticleResponse> articleResponseList = new ArrayList<>();
//        for (Article article : articleList){
//            ArticleResponse articleResponse = new ArticleResponse(article);
//            articleResponseList.add(articleResponse);
//        }
        return ResponseEntity.ok().body(articleResponseList);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = articleService.getArticle(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Long> deleteArticle(@PathVariable long id){
        articleService.delete(id);

        return ResponseEntity.ok().body(id);
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
        Article updatedArticle = articleService.update(id, request);

        return ResponseEntity.ok().body(updatedArticle);
    }
}
