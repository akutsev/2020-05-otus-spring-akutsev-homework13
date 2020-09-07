package ru.otus.akutsev.books.integrationtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Genre;
import ru.otus.akutsev.books.security.DataSourceConfig;
import ru.otus.akutsev.books.service.BookService;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест приложения с поднятием контекста")
@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@DisplayName("добавление+извлечение книги")
	@Test
	@WithMockUser(username = "Alex", authorities = {"ROLE_ADMIN"})
	public void addBookGetByIdTest() {
		String authorName = "Lev Tolstoy";
		Author author = new Author();
		author.setName(authorName);

		String genreName = "Drama";
		Genre genre = new Genre();
		genre.setGenreName(genreName);

		String bookName = "Anna Karenina";
		Book book = new Book();
		book.setName(bookName);
		book.setAuthor(author);
		book.setGenre(genre);

		assertFalse(bookService.getAll().contains(book));

		long id = bookService.save(book).getId();

		Book bookFromDb = bookService.getAById(id);
		assertEquals(bookName, bookFromDb.getName());
		assertEquals(author.getName(), bookFromDb.getAuthor().getName());
		assertEquals(genre.getGenreName(), bookFromDb.getGenre().getGenreName());
	}

	@DisplayName("Изменение полей книги")
	@Test
	@WithMockUser(username = "Alex", authorities = {"ROLE_ADMIN"})
	public void updateBookTest() {
		long id = 1;
		Book book = bookService.getAById(id);

		String authorName = "Fedor Dostoevskiy";
		Author newAuthor = new Author();
		newAuthor.setName(authorName);


		String genreName = "Psychological criminal drama";
		Genre newGenre = new Genre();
		newGenre.setGenreName(genreName);

		String bookName = "Crime and punishment";

		bookService.updateBook(book, bookName, newAuthor, newGenre);

		assertEquals(bookName, book.getName());
		assertEquals(newAuthor, book.getAuthor());
		assertEquals(newGenre, book.getGenre());
	}

	@DisplayName("Изменение полей книги - нет доступа")
	@Test
	@WithMockUser(username = "Otus", authorities = {"ROLE_USER"})
	public void updateBookTest_AccessDenied() {
		long id = 1;
		assertThrows(AccessDeniedException.class, () -> bookService.getAById(id));
	}

	@DisplayName("Изменение полей книги - есть доступ")
	@Test
	@WithMockUser(username = "Otus", authorities = {"ROLE_USER"})
	public void updateBookTest_AccessAllowed() {
		long id = 3;
		assertDoesNotThrow(() -> bookService.getAById(id));
	}

	@DisplayName("Удаление книги - нет доступа")
	@Test
	@WithMockUser(username = "Otus", authorities = {"ROLE_USER"})
	public void deleteBookTest_AccessDenied() {
		long id = 1;
		assertThrows(AccessDeniedException.class, () -> bookService.deleteById(id));
	}

	@DisplayName("Удаление книги - есть доступ")
	@Test
	@WithMockUser(username = "Alex", authorities = {"ROLE_ADMIN"})
	public void deleteBookTest_AccessAllowed() {
		long id = 2;
		assertDoesNotThrow(() -> bookService.deleteById(id));
	}
}
