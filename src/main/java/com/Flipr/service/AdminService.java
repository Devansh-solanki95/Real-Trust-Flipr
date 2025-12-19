package com.Flipr.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Flipr.Repository.AdminRepository;
import com.Flipr.entity.Admin;
import com.Flipr.model.AdminModel;




@Service
public class AdminService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdminRepository adminRepo;
	

	public Boolean saveAdmin(AdminModel model) {
		// TODO Auto-generated method stub
		
		String rawPassword = model.getPassword();
		String hashedPass = passwordEncoder.encode(rawPassword);
		
		//Admin newAdmin = new Admin();
		 Admin newAdmin = new Admin(model.getName(), model.getEmail(), hashedPass , "ADMIN");
		/*
		 * newAdmin.setContact_no(model.getContact_no());
		 * newAdmin.setEmail(model.getEmail()); newAdmin.setName(model.getName());
		 * newAdmin.setPassword(model.getPassword());
		 */
		
		adminRepo.save(newAdmin);
		
		System.out.println("admin saved successfully");
		
		return true;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Admin> op = adminRepo.findByEmail(email);
		System.err.println(email);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}
	
	public Admin getById(int userid) {
		return adminRepo.findById(userid).get();
	}

	

}
