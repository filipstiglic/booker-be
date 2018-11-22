package sk.isdd.workshop.bookerbe.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 * @Author Filip Stiglic
 */
public class AuthorDTO implements Serializable {

	private Long id;

	@NotEmpty
	@Length(max = 32)
	private String name;

	@NotEmpty
	@Length(max = 32)
	private String surname;

	@NotEmpty
	@Length(max = 32)
	private String country;

	@Version
	@Column(nullable = false)
	private long recordVersion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public long getRecordVersion() {
		return recordVersion;
	}

	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}
}
