package com.carrots.springbootdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrots.springbootdemo.entity.Project;
import com.carrots.springbootdemo.exception.ProjectIdException;
import com.carrots.springbootdemo.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception e) {
			throw new ProjectIdException("Project Id: " + project.getProjectIdentifier() + 
											" already exists");
		}
	}
	
	public Project findProjectById(String projectIdentifier) {
		
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase()); 
		
		if(project == null) {
			throw new ProjectIdException("Project Id: " + projectIdentifier + 
											" does not exist");
		}
		
		return project;
		
	}

	public List<Project> findAllProjects() {
		
		List<Project> projects = projectRepository.findAll();
		
		if(projects.size() == 0) {
			System.out.println("No projects found");
		}
		
		return projects;
		
	}

	public void deleteProject(Project project) {
		
		projectRepository.delete(project);
		
	}
	
}
