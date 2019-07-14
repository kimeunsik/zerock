package org.zerock.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
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
}
