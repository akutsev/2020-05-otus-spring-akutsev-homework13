package ru.otus.akutsev.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.akutsev.books.dao.GenreDao;
import ru.otus.akutsev.books.model.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

	private final GenreDao genreDao;

	@Autowired
	public GenreServiceImpl(GenreDao genreDao) {
		this.genreDao = genreDao;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Genre> findAll() {
		return genreDao.findAll();
	}
}
