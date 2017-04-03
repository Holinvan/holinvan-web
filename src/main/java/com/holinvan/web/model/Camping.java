package com.holinvan.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="campings")
public class Camping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	@Size(max=30)
	private String address;
	@Size(max=30)
	private String addressf;
	@Size(max=10)
	private String cif;
	@Size(max=25)
	private String city;
	@Size(max=25)
	private String country;
	@Size(max=10)
	private String cp;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Size(max=20)
	private String schedule;
	@Email
	@Size(max=30)
	private String emailf;
	private Integer idTelephone;
	private String name;
	private String namef;
	private Integer rates;
	private float rating;
	private String telephone;
	private String location;
	private String zone;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Camping() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressf() {
		return this.addressf;
	}

	public void setAddressf(String addressf) {
		this.addressf = addressf;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailf() {
		return this.emailf;
	}

	public void setEmailf(String emailf) {
		this.emailf = emailf;
	}

	public Integer getIdTelephone() {
		return this.idTelephone;
	}

	public void setIdTelephone(Integer idTelephone) {
		this.idTelephone = idTelephone;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamef() {
		return this.namef;
	}

	public void setNamef(String namef) {
		this.namef = namef;
	}

	public Integer getRates() {
		return this.rates;
	}

	public void setRates(Integer rates) {
		this.rates = rates;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

}