package com.Flipr.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Flipr.Repository.ProjectRepository;
import com.Flipr.entity.Projects;
import com.Flipr.model.ProjectModel;


@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepo;
	
    // Save new project with image and team
    public Projects saveProject(ProjectModel model) {
        Projects project = new Projects();

        project.setName(model.getName());
        project.setDescription(model.getDescription());

        try {
            if (model.getImage() != null && !model.getImage().isEmpty()) {
                project.setImage(model.getImage().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading image file", e);
        }


        return projectRepo.save(project);
    }

    // Get all projects with members
    public List<Projects> getAllProjects() {
        return projectRepo.findAll();
    }

}
