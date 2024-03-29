package com.carrots.springbootdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrots.springbootdemo.entity.Project;
import com.carrots.springbootdemo.service.ProjectService;
import com.carrots.springbootdemo.service.ValidationMapService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ValidationMapService mapValidationService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project,
													BindingResult result){
		
		if(result.hasErrors()) {
			return mapValidationService.mapValidation(result);
		}
		
		projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED); 
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getProjectById(@RequestParam("projectId") String projectId){
		
		Project project = projectService.findProjectById(projectId);
		
		return new ResponseEntity<Project>(project, HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllProjects(){
		
		List<Project> projects = projectService.findAllProjects();
		
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProject(@RequestParam("projectId") String projectId){
		
		Project project = projectService.findProjectById(projectId);
		projectService.deleteProject(project);
		
		return new ResponseEntity<Project>(project, HttpStatus.OK);
		
	}
	
}
