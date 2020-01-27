package com.example.chorong.springboot.domain.posts;

import com.example.chorong.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Setter 어노테이션 없음 -> 무작정으로 getter,setter 생성시 해당 클래스의 인스턴스 값들이 언제 어디서 변해야하는지 코드상으로 명확하게 구분하기 어려움.
// 따라서 Entity 클래스에서는 절대 Setter 메소드를 만들지 않음.
// 대신, 해당 필드의 값 변경이 필요하면 그 목적과 의도가 명확하게 드러나는 메소드를 추가해야 함.

// 그럼 이때, Setter 가 없는데, 어떻게 값을 채워 DB에 삽입하나?
// 기본적인 구조는 생성자를 통해 최종값을 채운 후 DB 에 삽입.
// 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출해서 변경하는 것을 전제로 함.
@Getter //클래스 내 모든 필드의 Getter 메소드 자동생
@NoArgsConstructor //기본 생성자 자동 추가. public Posts(){} 와 같은 효과
@Entity //테이블과 링크될 클래스. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭함.
public class Posts  extends BaseTimeEntity {


    @Id //해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성규칙. 스프링부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야함 auto_increment 가 가능
    //웬만하면 ENTITY의 PK는 Long 타입의 Auto_increment 추천.
    private Long id;

    //테이블의 칼럼을 나타냄. 굳이 선언하지 않더라고 해당 클래스의 필드는 모두 칼럼이 됨.
    //문자열의 경우 VARCHAR(255)가 기본인데 , 사이즈를 늘리고 싶거나 타입을 TEXT로 변경하고 싶거다 등의 경우에 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }



}
