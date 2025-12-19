package com.Flipr.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Flipr.Repository.ClientRepository;
import com.Flipr.entity.Clients;
import com.Flipr.entity.Projects;
import com.Flipr.model.ClientModel;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepo;

	public Clients saveClient(ClientModel model) {
		 Clients client = new Clients();

	         client.setName(model.getName());
	        client.setDescription(model.getDescription());

	        try {
	            if (model.getImage() != null && !model.getImage().isEmpty()) {
	                client.setImage(model.getImage().getBytes());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Error reading image file", e);
	        }
	        
	        client.setDesignation(model.getDesignation());


	        return clientRepo.save(client);
	        
	}

	public List<Clients> getAllClients() {
		// TODO Auto-generated method stub
		return clientRepo.findAll();
	}
	

}
