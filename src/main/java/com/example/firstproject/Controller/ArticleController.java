package com.example.firstproject.Controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j //로깅을 위한 어노테이션 추가
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleFrom(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
       log.info(form.toString());
        //System.out.println(form.toString());

        //DTO를 엔티티로 변환
        Article article=form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());

        //레파지토리로 엔티티를 DB에 저장
        Article saved=articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("articles/{id}") //데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){ //매개변수로 id 받아 오기
        log.info("id=" +id); //id를 잘 받았는지 확인하는 로그 찍기

        //1.id를 조회해 데이터 가져오기: DB에서 데이터를 가져오는 주체는 repository 이다.
        Article articleEntity=articleRepository.findById(id).orElse(null);
        //2.모델에 데이터 등록하기 : Model 객체 매개변수에 넣기
        model.addAttribute("article",articleEntity);
        //3.뷰 페이지 반환하기
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        List<Article> articleEntityList=(List<Article>) articleRepository.findAll(); //형변환을 해 준다
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        //3. 뷰 페이지 설정하기
        return  "articles/index";

    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity=articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ //매개변수로 dto 받아 오기
        log.info(form.toString());
        return "";
    }

}
