package sk.isdd.workshop.bookerbe.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * @Author Filip Stiglic
 */
public class BookDTO implements Serializable {

	private Long id;

	@NotEmpty
	@Length(max = 32)
	private String title;

	@NotEmpty
	@Length(max = 32)
	private String yearPublished;

	private long recordVersion;

	@NotNull
	private BookDetailDTO bookDetailDTO;

	@NotNull
	private Long authorId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public long getRecordVersion() {
		return recordVersion;
	}

	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public BookDetailDTO getBookDetailDTO() {
		return bookDetailDTO;
	}

	public void setBookDetailDTO(BookDetailDTO bookDetailDTO) {
		this.bookDetailDTO = bookDetailDTO;
	}
}
