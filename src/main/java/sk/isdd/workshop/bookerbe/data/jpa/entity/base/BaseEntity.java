package sk.isdd.workshop.bookerbe.data.jpa.entity.base;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @Author Filip Stiglic
 */

@MappedSuperclass
public abstract class BaseEntity {

	@Version
	@Column(nullable = false)
	protected long recordVersion;

	@CreationTimestamp
	@Column(nullable = false)
	protected LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(nullable = false)
	protected LocalDateTime lastModifiedDate;

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
}
