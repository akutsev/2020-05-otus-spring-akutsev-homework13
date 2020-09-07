package ru.otus.akutsev.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Genre;
import ru.otus.akutsev.books.service.AuthorService;
import ru.otus.akutsev.books.service.BookService;
import ru.otus.akutsev.books.service.GenreService;

import java.util.List;

@Controller
public class BooksController {

	private final BookService bookService;
	private final AuthorService authorService;
	private final GenreService genreService;

	@Autowired
	public BooksController(BookService bookService, AuthorService authorService, GenreService genreService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.genreService = genreService;
	}

	@GetMapping("/")
	public String listPage(Model model) {
		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "allBooks";
	}

	@GetMapping("/save")
	public String saveEditedBook(Model model) {
		List<Author> authors = authorService.getAll();
		List<Genre> genres = genreService.findAll();

		model.addAttribute("authors", authors);
		model.addAttribute("genres", genres);

		return "save";
	}

	@GetMapping("/edit")
	public String editPage(@RequestParam("id") long id, Model model) {
		Book book = bookService.getAById(id);
		List<Author> authors = authorService.getAll();
		List<Genre> genres = genreService.findAll();

		model.addAttribute("book", book);
		model.addAttribute("authors", authors);
		model.addAttribute("genres", genres);

		return "edit";
	}

	@PostMapping("/edit")
	public String saveEditedBook(Book book) {
		Book bookSaved = bookService.save(book);

		return "redirect:/";
	}

	@PostMapping("/delete")
	public String deleteBook(long id) {
		bookService.deleteById(id);

		return "redirect:/";
	}

	@PostMapping("/save")
	public String saveNewBook(Book book) {
		bookService.save(book);

		return "redirect:/";
	}
}
