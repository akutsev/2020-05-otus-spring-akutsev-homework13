package ru.otus.akutsev.books.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "text")
	private String text;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	public Comment(int id, String text, Book book) {
		this.id = id;
		this.text = text;
		this.book = book;
	}

	public Comment() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Comment)) return false;
		Comment comment = (Comment) o;
		return id == comment.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Comment{" +
				"text='" + text + '\'' +
				'}';
	}
}
