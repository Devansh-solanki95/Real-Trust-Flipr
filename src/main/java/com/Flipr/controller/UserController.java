package com.Flipr.controller;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow.Subscriber;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Flipr.Repository.ContactRepository;
import com.Flipr.Repository.SubscriptionRepository;
import com.Flipr.entity.Clients;
import com.Flipr.entity.Contact;
import com.Flipr.entity.Projects;
import com.Flipr.entity.Subscription;
import com.Flipr.model.ApiResponse;
import com.Flipr.model.ContactModel;
import com.Flipr.model.SubscribeModel;
import com.Flipr.service.ClientService;
import com.Flipr.service.ProjectService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private SubscriptionRepository subRepo;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/save")
    public ResponseEntity<ApiResponse> saveContact(@RequestBody ContactModel contact) {
      Contact newContact = new Contact();
     newContact.setName(contact.getName());
     newContact.setEmail(contact.getEmail());
     newContact.setMobileNo(contact.getMobileNo());
     newContact.setCity(contact.getCity());
     
     contactRepo.save(newContact);
        return ResponseEntity.ok(new ApiResponse(true, "contact saved ", newContact));
    }
	
	@PostMapping("/subscribe")
	public ResponseEntity<ApiResponse> subscribe(@RequestBody SubscribeModel model) {
		//TODO: process POST request
		Subscription newSubcriber = new Subscription();
		newSubcriber.setEmail(model.getEmail());
	    
		subRepo.save(newSubcriber);
	    return ResponseEntity.ok(new ApiResponse(true, "Subscription saved ", newSubcriber ));
	}
	
	   @GetMapping("/allproject")
	    public ResponseEntity<ApiResponse> getAllProjects() {
	        List<Projects> projects = projectService.getAllProjects();

	        List<Object> modified = projects.stream()
	                .map(this::convertToResponse)
	                .collect(Collectors.toList());

	        return ResponseEntity.ok(new ApiResponse(true, "Projects fetched successfully", modified));
	    }
	   
	   
	   
	
	   private Map<String, Object> convertToResponse(Projects project) {
	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("projectID", project.getId());
	        map.put("name", project.getName());
	        map.put("description", project.getDescription());
	      

	        // ✅ Project image base64 (match frontend key)
	        if (project.getImage() != null && project.getImage().length > 0) {
	            String base64Image = Base64.getEncoder().encodeToString(project.getImage());
	            map.put("imageBase64", base64Image); // ✅ Correct key
	        } else {
	            map.put("imageBase64", null);
	        }

	       
	        return map;
	    }
	   
	   
	   
	   @GetMapping("/allclients")
	    public ResponseEntity<ApiResponse> getAllClients() {
	        List<Clients> clients = clientService.getAllClients();

	        List<Object> modified = clients.stream()
	                .map(this::convertToResponse)
	                .collect(Collectors.toList());

	        return ResponseEntity.ok(new ApiResponse(true, "Clients fetched successfully", modified));
	    }
	   
	   private Map<String, Object> convertToResponse(Clients clients) {
	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("clientID", clients.getId());
	        map.put("name", clients.getName());
	        map.put("description", clients.getDescription());
	        map.put("designation", clients.getDesignation());

	        // ✅ Project image base64 (match frontend key)
	        if (clients.getImage() != null && clients.getImage().length > 0) {
	            String base64Image = Base64.getEncoder().encodeToString(clients.getImage());
	            map.put("imageBase64", base64Image); // ✅ Correct key
	        } else {
	            map.put("imageBase64", null);
	        }

	       
	        return map;
	    }


}
