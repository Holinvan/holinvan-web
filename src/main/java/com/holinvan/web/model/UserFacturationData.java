package com.holinvan.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the user_facturation_data database table.
 * 
 */
@Entity
@Table(name="user_facturation_data")
public class UserFacturationData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	@Size(max=30)
	private String addressf;
	@NotEmpty
	@Email
	private String emailf;
	@NotEmpty
	@Size(max=30)
	private String namef;
	@NotEmpty
    @Size(max=10)
	private String niff;

	private User user;

	public UserFacturationData() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddressf() {
		return this.addressf;
	}

	public void setAddressf(String addressf) {
		this.addressf = addressf;
	}

	public String getEmailf() {
		return this.emailf;
	}

	public void setEmailf(String emailf) {
		this.emailf = emailf;
	}

	public String getNamef() {
		return this.namef;
	}

	public void setNamef(String namef) {
		this.namef = namef;
	}

	public String getNiff() {
		return this.niff;
	}

	public void setNiff(String niff) {
		this.niff = niff;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}