package com.Flipr.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {
	
	private String name;
    private String description;
    private String designation;
	private MultipartFile image;
    

}
