package ru.otus.akutsev.books.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	public Author(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Author() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Author)) return false;
		Author author = (Author) o;
		return id == author.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
