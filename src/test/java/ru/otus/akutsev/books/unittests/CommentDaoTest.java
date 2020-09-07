package ru.otus.akutsev.books.unittests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.akutsev.books.dao.CommentDao;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест ДАО работы с комментариями")
@DataJpaTest
public class CommentDaoTest {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private TestEntityManager entityManager;

	@DisplayName("вставка нового комментария и получение его по ИД")
	@Test
	public void addCommentGetByIdTest() {
		long bookId = 1;
		Book book = entityManager.find(Book.class, bookId);

		String text = "Very booooooring book";
		Comment comment = new Comment();
		comment.setText(text);
		comment.setBook(book);

		long id = commentDao.save(comment).getId();

		entityManager.clear();
		Comment commentFromDb = commentDao.findById(id).get();

		assertEquals(text, commentFromDb.getText());
		assertEquals(book, commentFromDb.getBook());
	}

	@DisplayName("удаление комментария")
	@Test
	public void deleteGenreTest() {
		long id = 4;
		Comment commentToDelete = entityManager.find(Comment.class, id);

		assertNotEquals(Optional.empty(), commentDao.findById(id));
		commentDao.delete(commentToDelete);
		assertEquals(Optional.empty(), commentDao.findById(id));
	}

	@DisplayName("извлечение всех комментариев книги")
	@Test
	public void getAllCommentsByBook() {
		long id = 1;
		Book book = entityManager.find(Book.class, id);

		List<Comment> comments = commentDao.findByBook(book);

		String[] expected = {"Book that you will never forget, 5 stars!", "Honestly I did not like that, 3 stars"};
		String[] actual = comments.stream()
				.map(comment -> comment.getText())
				.toArray(String[]::new);

		assertArrayEquals(expected, actual);
	}
}
