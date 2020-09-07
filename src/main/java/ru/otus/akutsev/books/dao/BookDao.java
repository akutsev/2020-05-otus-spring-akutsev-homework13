package ru.otus.akutsev.books.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {
	Book save(Book book);

	Optional<Book> findAById(long id);

	@EntityGraph(attributePaths = {"author", "genre"})
	@Override
	List<Book> findAll();

	void delete(Book book);
}
