package com.example.chorong.springboot.domian.posts;

import com.example.chorong.springboot.domain.posts.Posts;
import com.example.chorong.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    //Junit 에서 단위테스트가 끝날 때 마다 수행되는 메소드 지정
    //보통 배포 전 전체 테스트 수행할 때 테스타 간 데이터 침범을 막기 위해 사용
    // 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데어터가 그대로 남아 다음 테스트 실행 시 테스트 실패할 수 있음.
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //테이블 posts에 insert/update 쿼리 실행
        //id 값이 있으면 update, 없으면 insert 쿼리 실행됨.
        postsRepository.save(Posts.builder().title(title).content(content).author("or9436@gmail.com").build());

        //테이블 posts에 있는 모든 데이터를 조회해오는 메소드
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2020,1,24,0,0,0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> CreateDate = " +posts.getCreatedDate()+ " , modifiedDate = " + posts.getModifiedDate() );

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
