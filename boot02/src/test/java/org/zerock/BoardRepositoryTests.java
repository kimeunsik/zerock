package org.zerock;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Board;
import org.zerock.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepo;
	
//	@Test
	public void inspect() {
		
		//실제 객체의 클래스 이름
		Class<?> clz = boardRepo.getClass();
		
		System.out.println("클래스 이름: " + clz.getName()); //com.sun.proxy로 시작(동적 프록시 기능에 의해 동적으로 생성된 클래스)
		System.out.println("클래스가 구현하고 있는 인터페이스 목록");
		Class<?>[] interfaces = clz.getInterfaces();
		Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));
		
		System.out.println("클래스의 부모 클래스");
		Class<?> superClasses = clz.getSuperclass();
		System.out.println(superClasses.getName());
	}
	
//	@Test
	public void testInsert() {
		
		Board board = new Board();
		board.setTitle("게시물의 제목");
		board.setContent("게시물 내용 넣기....");
		board.setWriter("user00");
		
		boardRepo.save(board);
	}
	
//	@Test
	public void testRead() {
		
		Board board = boardRepo.findOne(1L);
		
		System.out.println(board);
	}
	
//	@Test
	public void testUpdate() {
		
		System.out.println("Read First...............");
		Board board = boardRepo.findOne(1L);
		
		System.out.println("Update Title.............");
		board.setTitle("수정된 제목입니다.");
		
		System.out.println("Call Save()..............");
		boardRepo.save(board);
	}
	
	@Test
	public void testDelete() {
		
		System.out.println("Delete Entity");
		
		boardRepo.delete(1L);
	}
}
