package com.Flipr.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectModel {
	

	private String name;
    private String description;
	private MultipartFile image;
    
    
}
