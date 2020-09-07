package ru.otus.akutsev.books.service;

import ru.otus.akutsev.books.model.Genre;

import java.util.List;

public interface GenreService {

	List<Genre> findAll();
}
