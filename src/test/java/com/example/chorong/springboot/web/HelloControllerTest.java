package com.example.chorong.springboot.web;

import com.example.chorong.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//1) 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴.
//   여기서는 SpringRunner라는 스프링 실행자를 사용
//   즉, 스프링부트 테스트와 JUnit 사이의 연결자 역할을 함.
//2) 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
//   선언할 경우 @Controller, @ControllerAdvice 사용 가능
//   단, @Service, @Component, @Repository 등은 사용할 수 없음. 여기서는 컨트롤러만 사용하기 때문에 선언.
@RunWith(SpringRunner.class) //1)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = SecurityConfig.class)}) //2)
public class HelloControllerTest {

    //3) 웹 API를 테스트할 때 사용.
    //   스프링 MVC 테스트의 시작점.
    //   이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음.
    @Autowired
    private MockMvc mvc; //3)

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        //MockMvc를 통해 /hello 주소로 HTTP GET 요청함.
        mvc.perform(get("/hello")) //4)
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name).param("amount",String.valueOf(amount)))
        .andExpect(status().isOk()).andExpect(jsonPath("$.name", is(name)))
        .andExpect(jsonPath("$.amount", is(amount)));

    }
}
