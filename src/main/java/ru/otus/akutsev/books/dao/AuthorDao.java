package ru.otus.akutsev.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Author;

import java.util.Optional;

@Repository
public interface AuthorDao extends JpaRepository<Author, Long> {
	Author save(Author author);

	Optional<Author> findById(long id);

	void delete(Author author);
}