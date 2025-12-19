package com.Flipr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Flipr.entity.Clients;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Integer> {
	
	

}
