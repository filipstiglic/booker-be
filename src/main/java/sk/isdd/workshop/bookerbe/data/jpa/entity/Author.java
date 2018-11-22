package sk.isdd.workshop.bookerbe.data.jpa.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import sk.isdd.workshop.bookerbe.data.jpa.entity.base.BaseEntity;

/**
 * @Author Filip Stiglic
 */

@Entity
public class Author extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@NotEmpty
	@Length(max = 32)
	@Column(nullable = false, length = 32)
	private String name;

	@NotEmpty
	@Length(max = 32)
	@Column(nullable = false, length = 32)
	private String surname;

	@NotEmpty
	@Length(max = 32)
	@Column(nullable = false, length = 32)
	private String country;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> books;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBook(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book){
		book.setAuthor(this);
		if(books==null){
			books=new ArrayList<>();
		}
		books.add(book);
	}
}
