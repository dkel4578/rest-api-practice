package com.tj.edu.training.shinsunyoung.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tj.edu.training.shinsunyoung.model.Article;
import com.tj.edu.training.shinsunyoung.model.dto.AddArticleRequest;
import com.tj.edu.training.shinsunyoung.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class ArticleApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper om;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {
        //1. give
        final String url = "/api/articles";
        final String title = "제목";
        final String content = "내용";

        final AddArticleRequest userRequest = AddArticleRequest.builder()
                .title(title)
                .content(content)
                .build();
        final String requestBody =  om.writeValueAsString(userRequest);  // java class를 json string형태로 바꾸어줌
        //2. when
        ResultActions resultAction = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)  // request data를 json으로 받겠다.
                        .content(requestBody));
        //3. then
        resultAction.andExpect(status().isCreated());
        List<Article> articles = articleRepository.findAll();
        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }
    @Test
    public void findAllArticles() throws Exception {
        final String url = "/api/articles";

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
        List<Article> articles = articleRepository.findAll();
        assertThat(articles.size()).isEqualTo(3);
        assertThat(articles.get(0).getTitle()).isEqualTo("제목 1");
        assertThat(articles.get(2).getContent()).isEqualTo("내용 3");
    }

    @Test
    public void findArticle() throws Exception {
        final String url = "/api/articles/{id}";

        Article article = articleRepository.findById(2L).get();

        final ResultActions resultActions = mockMvc.perform(get(url, article.getId())
                .accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("내용 2"))
                .andExpect(jsonPath("$.title").value("제목 2"));

    }

    @Test
    public void deleteArticle() throws Exception{
        final String url = "/api/articles/{id}";

        Article article = articleRepository.findById(2L).get();

        final ResultActions resultActions = mockMvc.perform(delete(url, article.getId()))
                .andExpect(status().isOk());

        Optional<Article> article2 = articleRepository.findById(2L);
        assertThat(article2).isEmpty();
    }
}