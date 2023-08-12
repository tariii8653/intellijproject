package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //REST API 용 컨트롤러
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello(){ //요청 접수
      return "hello world";  //문자열 반환
    };
}
