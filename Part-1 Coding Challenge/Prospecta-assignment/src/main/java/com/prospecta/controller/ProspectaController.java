package com.prospecta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prospecta.model.Entry;
import com.prospecta.model.Result;
import com.prospecta.model.Value;
import com.prospecta.service.ProspectaService;

@RestController
public class ProspectaController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProspectaService service;
	
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<Result>> getTitleAndDescriptionByCategoryHandler(@PathVariable String category){
		
		String[] arr = category.split(" ");
		
		if(arr.length > 1) {
			category = arr[0]; // taking 1st word as category, if there is a space
		}
		
		Value data = restTemplate.getForObject("https://api.publicapis.org/entries", Value.class);
		
		List<Entry> entries = data.getEntries();
		
		List<Result> result = new ArrayList<>();
		
		for(Entry entry : entries) {
			
			if(entry.getCategory().equalsIgnoreCase(category))
				result.add(new Result(entry.getApi(), entry.getDescription()));
		}
		
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	@PostMapping("/entries")
	public ResponseEntity<String> saveEntryHandler(@RequestBody Entry entry){
		
        Value data = restTemplate.getForObject("https://api.publicapis.org/entries", Value.class);
		
		List<Entry> entries = data.getEntries();
		entries.add(entry);
		
		service.saveEntry(entries); 
		
		return new ResponseEntity<String>("Entry saved Successfully",HttpStatus.CREATED);
	}
	
	
}
