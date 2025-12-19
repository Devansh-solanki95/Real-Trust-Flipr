package com.Flipr.controller;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Flipr.Repository.ContactRepository;
import com.Flipr.config.JwtUtils;
import com.Flipr.entity.Admin;
import com.Flipr.entity.Clients;
import com.Flipr.entity.Contact;
import com.Flipr.entity.Projects;
import com.Flipr.model.AdminModel;
import com.Flipr.model.ApiResponse;
import com.Flipr.model.ClientModel;
import com.Flipr.model.LoginModel;
import com.Flipr.model.LoginResponse;
import com.Flipr.model.ProjectModel;
import com.Flipr.service.AdminService;
import com.Flipr.service.ClientService;
import com.Flipr.service.ProjectService;






@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
    private ProjectService projectService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ContactRepository contactRepo;
	
    @PostMapping("/saveAdmin")
    public ResponseEntity<ApiResponse> saveAdmin(@RequestBody AdminModel model) {
        Boolean status = adminService.saveAdmin(model);
        return ResponseEntity.ok(new ApiResponse(status, status ? "Admin saved successfully" : "Admin not saved", model));
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login( @RequestBody LoginModel model) {

        Admin user = (Admin) adminService.loadUserByUsername(model.getEmail());

        System.err.println(model.getEmail());
        // ✅ FIX 1: Null check FIRST
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, "User not found", null));
        }
        
        System.err.println(user);

        String rawPassword = model.getPassword();
        System.err.println(model.getPassword());
        
        System.err.println(user.getPassword());
        String storedPassword = user.getPassword();
        
        System.err.println(storedPassword);
        

        boolean passwordMatches;

        if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$")) {
            passwordMatches = passwordEncoder.matches(rawPassword, storedPassword);
        } else {
            passwordMatches = rawPassword.equals(storedPassword);
        }

        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, "Invalid credentials", null));
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        model.getEmail(),
                        model.getPassword()
                )
        );

        String token = jwtUtils.generateToken(user.getUserId());

        return ResponseEntity.ok(
                new ApiResponse(true,
                        "Login Successfully as : " + user.getRole(),
                        new LoginResponse(token,user.getRole()))
        );
    }
    
    @PostMapping(value = "/addProject", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse> uploadProject(@ModelAttribute ProjectModel model) {
        try {
            Projects saved = projectService.saveProject(model);
            return ResponseEntity.ok(new ApiResponse(true, "Project saved", convertToResponse(saved)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Project not saved", null));
        }
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


    @PostMapping(value = "/addClient", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse> addClient(@ModelAttribute ClientModel model) {
        try {
            Clients saved = clientService.saveClient(model);
            return ResponseEntity.ok(new ApiResponse(true, "Project saved", convertToResponse(saved)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Project not saved", null));
        }
    }
    
    private Map<String, Object> convertToResponse(Clients clients) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("projectID", clients.getId());
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
    
    @GetMapping("/getAllContacts")
    public List<Contact> getAllContacts(){
        return contactRepo.findAll();
    }
    

	
}
