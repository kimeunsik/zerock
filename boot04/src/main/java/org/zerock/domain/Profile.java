package org.zerock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "member")
@Entity
@Table(name="tbl_profile")
@EqualsAndHashCode(of="fno")
public class Profile {

	/**
	 * 스프링부트 1.x버전을 이용할때 AUTO로 지정하면 칼럼이 auto_increment로 지정
	 * 스프링부트 2.x버전의 경우 AUTO로 지정하면 hibernate_sequence라는 테이블을 생성하고 번호를 유지하는 방식으로 변경됨
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fno;
	private String fname;
	private boolean current; 
	
	@ManyToOne
	private Member member;
}
