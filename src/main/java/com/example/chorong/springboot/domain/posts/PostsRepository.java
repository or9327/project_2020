package com.example.chorong.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 단순히 인터페이스 생성 후 JpaRepository<Entity클래스, PK타입> 을 상속하면 기본적인 crud 메소드 자동 생성
// entity 클래스와 기본 entity repository 는 함께 위치해야 함.
// 프로젝트가 커져서 도메인별로 프로젝트를 분리해야하면 Entity 클래스와 기본 Repository 는 함께 움직어야 하므로 도메인 패키지에서 함꼐 관리.
public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //SpringDataJpa 에서 제공하지 않는 메소드는 이처럼 쿼리로 작성해도 됨.
    List<Posts> findAllDesc();
}
