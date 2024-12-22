package com.cts.sms.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.sms.dto.ParentsSaveDto;
import com.cts.sms.entity.Parents;
import com.cts.sms.exception.ParentsNotFoundException;
import com.cts.sms.service.ParentsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/parents")
public class ParentsController {
	
	
	@Autowired
	private ParentsService parentService;
	
	private static final Logger logger = LoggerFactory.getLogger(ParentsController.class);
	
//	This method will return the list of all the parents
	@GetMapping("/")
	public List<Parents> findAllParents() throws ParentsNotFoundException
	{
		logger.info("Fetching the list of parents");
		return parentService.findAllParents();
		
	}

//	This method will add the parent to the database
	@PostMapping("/")
	public ResponseEntity<Parents> saveParent(@Valid @RequestBody ParentsSaveDto parent)
	{
		ResponseEntity<Parents>parents = new ResponseEntity<>(parentService.saveParent(parent), HttpStatus.CREATED);
		if(parents == null)
		{
			logger.info("Unable to add the parents");
		}
		logger.info("Parent added sucessfully");
		return parents;
	}
	
//	This method will delete the parent using id
	@DeleteMapping("/{id}")
	public void deleteParentById(@PathVariable Long id)
	{
		parentService.deleteParentById(id);
		logger.info("Parent Delete Sucessfully");
	}
	
	@GetMapping("/find-by-student-id/{id}")
	public ArrayList<Parents> findParentByStudentId(@PathVariable Long id) throws ParentsNotFoundException
	{
		ArrayList<Parents> parents = parentService.findParentByStudentId(id);
		if(parents.isEmpty())
		{
			logger.info("Unable to find the student");
			throw new ParentsNotFoundException("Unable to find the specified parent");
		}
		logger.info("Parent found");
		return parents;
	}
	
	//this method will update the parents data 
	@PutMapping("/update")
	public Parents updateParents(@RequestBody Parents parent) throws ParentsNotFoundException
	{
		return parentService.updateParent(parent);
	}
}	


	
