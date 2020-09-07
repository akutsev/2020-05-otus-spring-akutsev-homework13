package ru.otus.akutsev.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Genre;

import java.util.Optional;

@Repository
public interface GenreDao extends JpaRepository<Genre, Long> {
	Genre save(Genre genre);

	Optional<Genre> findById(long id);

	void delete(Genre genre);
}
