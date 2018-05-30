package org.hibernate.bugs;

import javax.persistence.*;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
