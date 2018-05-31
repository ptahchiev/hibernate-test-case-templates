package org.hibernate.bugs;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@MappedSuperclass
public class AbstractEntity {

	@Id
	private Long id;

	@Column(name = "code", nullable = false, unique = true)
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
