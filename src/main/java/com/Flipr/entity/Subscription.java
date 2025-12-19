package com.Flipr.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="subscription")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	
	@CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime time;

	public Subscription(String email, LocalDateTime time) {
		super();
		this.email = email;
		this.time = time;
	}

	
	
}
