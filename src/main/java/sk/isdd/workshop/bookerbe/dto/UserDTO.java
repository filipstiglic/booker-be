package sk.isdd.workshop.bookerbe.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * @Author Filip Stiglic
 */
public class UserDTO implements Serializable {

	private Long id;

	@NotEmpty
	@Length(max = 32)
	private String name;

	@NotEmpty
	@Length(max = 32)
	private String surname;

	@Email
	@NotEmpty
	@Length(max = 64)
	private String email;

	@NotEmpty
	@Length(max = 128)
	private String address;

	@NotEmpty
	@Length(max = 32)
	private String role;

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
}
