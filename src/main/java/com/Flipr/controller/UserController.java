package com.Flipr.controller;

import java.util.concurrent.Flow.Subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Flipr.Repository.ContactRepository;
import com.Flipr.Repository.SubscriptionRepository;
import com.Flipr.entity.Contact;
import com.Flipr.entity.Subscription;
import com.Flipr.model.ApiResponse;
import com.Flipr.model.ContactModel;
import com.Flipr.model.SubscribeModel;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private SubscriptionRepository subRepo;
	
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
	
		
	    return ResponseEntity.ok(new ApiResponse(true, "Subscription saved ", newSubcriber ));
	}
	

}
