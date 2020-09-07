package ru.otus.akutsev.books.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.akutsev.books.dao.AuthorDao;
import ru.otus.akutsev.books.model.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

	private final AuthorDao authorDao;

	public AuthorServiceImpl(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Author> getAll() {
		return authorDao.findAll();
	}
}
