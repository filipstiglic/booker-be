package sk.isdd.workshop.bookerbe.data.jpa.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import sk.isdd.workshop.bookerbe.data.jpa.entity.base.BaseEntity;

/**
 * @Author Filip Stiglic
 */

@Entity
public class BookDetail extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@NotNull
	@OneToOne
	@JoinColumn(name="book_id")
	private Book book;

	@NotEmpty
	@Length(max = 1024)
	@Column(nullable = false, length = 1024)
	private String description;

	@NotNull
	@Column(nullable = false)
	Integer numberOfPages;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
