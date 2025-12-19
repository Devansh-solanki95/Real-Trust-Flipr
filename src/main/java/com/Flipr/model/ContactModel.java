package com.Flipr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactModel {
	
	private String name;
	private String email;
	private String mobileNo;
	private String city;

}
