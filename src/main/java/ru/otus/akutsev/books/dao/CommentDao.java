package ru.otus.akutsev.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
	Comment save(Comment comment);

	Optional<Comment> findById(long id);

	List<Comment> findByBook(Book book);

	void delete(Comment comment);
}
