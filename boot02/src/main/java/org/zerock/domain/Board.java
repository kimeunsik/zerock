package org.zerock.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_boards")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	//AUTO: 특정 데이터베이스에 맞게 자동으로 생성되는 방식
	//IDENTITY: 기본키 생성 방식 자체를 데이터베이스에 위임하는 방식, 데이터베이스에 의존적인 방식, MySQL에서 주로 많이 사용
	//SEQUENCE: 데이터베이스의 시퀀스를 이용해서 식별키 생성(오라클에서 사용)
	//TABLE: 별도의 키를 생성해주는 채번 테이블(번호를 취할 목적으로 만든 테이블)을 이용하는 방식 
	private Long bno;
	
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp //엔티티가 생성될 시점의 날짜 데이터 기록
	private Timestamp regdate;
	
	@UpdateTimestamp //엔티티가 수정될 시점의 날짜 데이터 기록
	private Timestamp updatedate;
}
