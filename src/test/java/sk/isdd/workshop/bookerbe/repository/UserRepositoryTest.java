package sk.isdd.workshop.bookerbe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sk.isdd.workshop.bookerbe.constants.AuthorConstants.COUNTRY;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.DESCRIPTION;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.NUMBER_OF_PAGES;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.TITLE;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.YEAR_PUBLISHED;
import static sk.isdd.workshop.bookerbe.constants.UserConstants.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import sk.isdd.workshop.bookerbe.data.jpa.entity.Author;
import sk.isdd.workshop.bookerbe.data.jpa.entity.Book;
import sk.isdd.workshop.bookerbe.data.jpa.entity.BookDetail;
import sk.isdd.workshop.bookerbe.data.jpa.repository.AuthorRepository;
import sk.isdd.workshop.bookerbe.data.jpa.repository.BookRepository;
import sk.isdd.workshop.bookerbe.data.jpa.repository.UserRepository;
import sk.isdd.workshop.bookerbe.data.jpa.entity.User;

/**
 * @Author Filip Stiglic
 */

@Tag("RepositoryTest")
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;



	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	private BookDetail createBookDetail(){
		BookDetail bookDetail = new BookDetail();
		bookDetail.setDescription(DESCRIPTION);
		bookDetail.setNumberOfPages(NUMBER_OF_PAGES);
		return bookDetail;
	}

	private Book createBook(Author author){
		Book book = new Book();
		book.setTitle(TITLE);
		book.setYearPublished(YEAR_PUBLISHED);
		book.setBookDetail(createBookDetail());
		book.setAuthor(author);
        bookRepository.save(book);
		return book;
	}

	private Author createAuthor(){
		Author author = new Author();
		author.setName(NAME);
		author.setSurname(SURNAME);
		author.setCountry(COUNTRY);

		author = authorRepository.save(author);
		return author;
	}

	private User createUser(Book book){
		User user = new User();
		user.setAddress(ADDRESS);
		user.setName(NAME);
		user.setSurname(SURNAME);
		user.setEmail(EMAIL);
		user.setRole(ROLE);
		if(book!=null) {
			user.addBook(book);
		}
		userRepository.save(user);
		return user;
	}

	@Test
	public void emailUniqueViolationTest(){
		createUser(null);
		assertThrows(DataIntegrityViolationException.class, ()->createUser(null));
	}

	@Test
	public void deleteUserTest(){
		User createdUser = createUser(null);
		userRepository.deleteById(createdUser.getId());
		assertEquals(0,userRepository.count());
	}

	@Test
	public void validateUserTest(){

		Author author = createAuthor();
		Book book = createBook(author);

		User createdUser = createUser(book);

		User user = userRepository.findById(createdUser.getId()).get();
		assertEquals(ADDRESS, user.getAddress());
		assertEquals(NAME, user.getName());
		assertEquals(SURNAME, user.getSurname());
		assertEquals(EMAIL, user.getEmail());
		assertEquals(ROLE, user.getRole());

		user.setName(NAME+2);
		userRepository.save(user);
		testEntityManager.flush();
		User user2 = userRepository.findById(1L).get();
		assertEquals(1,user2.getRecordVersion());
	}

	@Test
	public void findAllUsersTest(){
		createUser(null);
		assertEquals(1,userRepository.count());
    }



}
