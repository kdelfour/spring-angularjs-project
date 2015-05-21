/**
 * 
 */
package com.netceler.afas.workbench.common.model;
import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

/**
 * Basic rule model
 * @author kdelfour
 *
 */
@Entity
@Audited
public class Rule  implements Serializable{

	/** Rule ident */
	@Id
    @GeneratedValue(strategy = AUTO)
    @Column
	private Long id;

	/** Rule title */
	@Column(length = 50, nullable = false, unique = true)
	private String title;
	
	/** Rule description */
	@Column(length = 200, nullable = true)
	private String description;
	
	/** Rule code */
	@Column(length = 1024, nullable = true)
	private String code;

	/**
	 * @return the ident
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param ident
	 *            the ident to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
