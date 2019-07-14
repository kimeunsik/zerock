package org.zerock.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zerock.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>{

	// title = ?
	public List<Board> findBoardByTitle(String title);

	// writer = ?
	public Collection<Board> findByWriter(String writer);

	// writer LIKE % ? %
	public Collection<Board> findByWriterContaining(String writer);
	
	// title LIKE % ? % OR content % ? %
	public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	// title LIKE % ? % AND bno > ?
	public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);
	
	// bno > ? ORDER BY bno DESC
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	// bno > ? ORDER BY bno DESC LIMIT ?, ?
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
	
	// 위와 동일하지만 정렬정보를 paging 안에 넣어 처리한다.
	public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);
	
	// 위와 동일하지만 정렬정보를 paging 안에 넣어 처리하며, Page 객체로 반환하여 페이징 정보를 추가로 제공
	public Page<Board> findPageByBnoGreaterThan(Long bno, Pageable paging);
	
	// @Query를 이용하여 JPQL(객체 쿼리)를 이용
	// %?1% : 첫번째 파라미터 값 대입
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByTitle2(String title);
	// %:content% : @Param("content") 값 대입
	@Query("SELECT b FROM Board b WHERE b.content LIKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByContent2(@Param("content") String content);
	// #{entityName} : Repository 인터페이스 정의시 <엔티티 타입, PK 타입>에서 엔티티 타입을 자동으로 참고(활용도가 떨어짐)
	@Query("SELECT b FROM #{#entityName} b WHERE b.writer LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByWriter2(String writer);
	//필요한 컬럼만 추출하는 경우
	@Query("SELECT b.bno, b.title, b.writer, b.regdate "
			+ " FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Object[]> findByTitle3(String title);
	
	//네이티브 쿼리(복잡한 쿼리를 작성할 때에는 유용, 하지만 데이터베이스에 종속적이라서 남용하지는 말아야 한다)
	@Query(value="select bno, title, writer from tbl_boards where title like CONCAT('%',?1,'%') and bno > 0 order by bno desc", nativeQuery = true)
	public List<Object[]> findByTitle4(String title);
	
	//@Query를 이용하더라도 페이징 처리를 하는 Pageable 인터페이스는 그대로 활용 가능(단, 네이티브 쿼리는 불가능)
	@Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByPage(Pageable pageable);

}
