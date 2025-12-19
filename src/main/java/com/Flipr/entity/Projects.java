package com.Flipr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projects {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Lob
	@Column(name = "image", columnDefinition = "LONGBLOB")
	private byte[] image;
	
	private String name;
	
	private String description;

	public Projects(byte[] image, String name, String description) {
		super();
		this.image = image;
		this.name = name;
		this.description = description;
	}

	
}
