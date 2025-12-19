package com.Flipr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Flipr.Repository.ContactRepository;
import com.Flipr.entity.Contact;
import com.Flipr.model.ApiResponse;
import com.Flipr.model.ContactModel;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private ContactRepository contactRepo;
	
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

}
