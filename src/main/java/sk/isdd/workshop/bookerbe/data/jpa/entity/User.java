package sk.isdd.workshop.bookerbe.data.jpa.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import sk.isdd.workshop.bookerbe.data.jpa.entity.base.BaseEntity;

/**
 * @Author Filip Stiglic
 */

@Entity
public class User extends BaseEntity {

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

	@Email
	@NotEmpty
	@Length(max = 64)
	@Column(nullable = false, length = 64)
	private String email;

	@NotEmpty
	@Length(max = 128)
	@Column(nullable = false, length = 128)
	private String address;

	@NotEmpty
	@Length(max = 32)
	@Column(nullable = false, length = 32)
	private String role;

	@ManyToMany
	@JoinTable(
		name = "UserBooks",
		joinColumns = { @JoinColumn(name = "user_id") },
		inverseJoinColumns = { @JoinColumn(name = "book_id") }
	)
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getRecordVersion() {
		return recordVersion;
	}

	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void addBook(Book book){
		if(books==null){
			books=new ArrayList<>();
		}
		books.add(book);
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
