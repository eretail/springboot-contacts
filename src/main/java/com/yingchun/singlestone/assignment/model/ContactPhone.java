package com.yingchun.singlestone.assignment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="h2Phone")
@Table(name = "h2_phone")
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContactPhone implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	private Integer phoneId;
	@JsonIgnore
	@Column(name = "contact_id")
	private Integer contactId;

	@JsonProperty("number")
	private String number;
	@JsonProperty("type")
	private String type;
	
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Contact.class)
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "id", insertable = false, updatable = false)
//	private Contact contact;
}