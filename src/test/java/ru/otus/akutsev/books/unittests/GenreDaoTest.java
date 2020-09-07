package ru.otus.akutsev.books.unittests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.akutsev.books.dao.GenreDao;
import ru.otus.akutsev.books.model.Genre;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест ДАО работы с жанрами")
@DataJpaTest
public class GenreDaoTest {

	@Autowired
	private GenreDao genreDao;

	@Autowired
	private TestEntityManager entityManager;

	@DisplayName("вставка нового жанра и получение его по ИД")
	@Test
	public void addGenreGetByIdTest() {
		String name = "Non fiction";
		Genre genre = new Genre();
		genre.setGenreName(name);

		long id = genreDao.save(genre).getId();

		entityManager.clear();
		Genre genreFromDb = genreDao.findById(id).get();

		assertEquals(name, genreFromDb.getGenreName());
	}

	@DisplayName("удаление жанра")
	@Test
	public void deleteGenreTest() {
		long id = 4;
		Genre genreToDelete = entityManager.find(Genre.class, id);
		assertNotEquals(Optional.empty(), genreDao.findById(id));

		genreDao.delete(genreToDelete);
		assertEquals(Optional.empty(), genreDao.findById(id));
	}
}
