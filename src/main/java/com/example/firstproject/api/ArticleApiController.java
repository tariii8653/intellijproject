package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;
    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return (List<Article>) articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);

    }

    //POST
   @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article=dto.toEntity();
        return articleRepository.save(article);
   }
   //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        //DTO ->엔티티 변환
        Article article=dto.toEntity();
        log.info("id :{}, article : {}", id,article.toString() );
        //타깃 조회하기
        Article target=articleRepository.findById(id).orElse(null);
        //잘못된 요청 처리하기
        if(target==null || id!=article.getId()){
            //400 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, aticle: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //업데이트 및 정상 응답하기
        Article updated=articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
}
