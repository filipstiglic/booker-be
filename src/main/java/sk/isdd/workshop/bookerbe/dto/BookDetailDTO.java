package sk.isdd.workshop.bookerbe.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * @Author Filip Stiglic
 */
public class BookDetailDTO implements Serializable {

	@NotEmpty
	@Length(max = 32)
	private String description;

	@NotNull
	private Integer numberOfPages;

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
}
