package com.Flipr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="contact")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name ;
	
	private String email;
	
	private String mobileNo;
	
	private String city;

	public Contact(String name, String email, String mobileNo, String city) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.city = city;
	}
	
	

}
