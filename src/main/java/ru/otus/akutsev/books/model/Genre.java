package ru.otus.akutsev.books.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genres")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "genre_name")
	private String genreName;

	public Genre(int id, String genreName) {
		this.id = id;
		this.genreName = genreName;
	}

	public Genre() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Genre)) return false;
		Genre genre = (Genre) o;
		return id == genre.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
