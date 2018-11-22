package sk.isdd.workshop.bookerbe.data.jpa.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import sk.isdd.workshop.bookerbe.data.jpa.entity.base.BaseEntity;

/**
 * @Author Filip Stiglic
 */

@Entity
public class Book extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@NotEmpty
	@Length(max = 128)
	@Column(nullable = false, length = 128)
	private String title;

	@NotEmpty
	@Length(max = 4)
	@Column(nullable = false, length = 4)
	private String yearPublished;

	@NotNull
	@OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private BookDetail bookDetail;

	@NotNull
	@ManyToOne
	@JoinColumn(name="author_id")
	private Author author;

	@ManyToMany(mappedBy = "books")
	private List<User> users;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(String yearPublished) {
		this.yearPublished = yearPublished;
	}

	public BookDetail getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(BookDetail bookDetail) {
		bookDetail.setBook(this);
		this.bookDetail = bookDetail;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user){
		if(users==null){
			users=new ArrayList<>();
		}
		users.add(user);
	}

}
