package ru.otus.akutsev.books.service;

import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;
import ru.otus.akutsev.books.model.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {

	Book save(Book book);
	Book getAById(long id);
	List<Book> getAll();
	List<Comment> getAllComments(Book book);
	Book updateBook (Book book, String newName, Author newAuthor, Genre newGenre);
	void deleteById(long id);

}
