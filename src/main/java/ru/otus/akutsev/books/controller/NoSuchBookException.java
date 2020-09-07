package ru.otus.akutsev.books.controller;

public class NoSuchBookException extends RuntimeException{
	public NoSuchBookException(String message) {
		super(message);
	}

	public NoSuchBookException() {
	}
}
