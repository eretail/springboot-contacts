package com.yingchun.singlestone.assignment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {
 * @author yli
 *	{
 *		  "name": {
 *		    "first": string,
 *		    "middle": string,
 *		    "last": string 
 *		  },
 *		  "address": {
 *		    "street": string,
 *		    "city": string,
 *		    "state": string,
 *		    "zip": string
 *		  },
 *		  "phone": [
 *		    {
 *		      "number": string,
 *		      "type": string ["home" | "work" | "mobile"]
 *		    }
 *		  ],
 *		  "email": string
 *		}
 * 
 * return json object
 * 
 *  "id": number,
 *	  "name": {
 *	    "first": string,
 *	    "middle": string,
 *	    "last": string 
 *	  },
 *	  "address": {
 *	    "street": string,
 *	    "city": string,
 *	    "state": string,
 *	    "zip": string
 *	  },
 *	  "phone": [
 *	    {
 *	      "number": string,
 *	      "type": string ["home" | "work" | "mobile"]
 *	    }
 *	  ],
 *	  "email": string
 *	}
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="h2contacts")
@Table(name = "h2_contact")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	@JsonProperty("name")
	ContactName contactName;
	/*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "phone_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_phone_contacts_id"
            ),
            insertable = false,
            updatable = false
    )*/
//	@ElementCollection(targetClass = java.util.ArrayList.class, fetch = FetchType.EAGER)
	@JsonProperty("phone")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "contact")
	List<ContactPhone> contactPhones=new ArrayList<ContactPhone>();
	@JsonProperty("address")
	ContactAddress contactAdress;
	@JsonProperty("email")
	String contactEmail;
}