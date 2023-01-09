package com.prospecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospecta.model.Entry;
import com.prospecta.repo.EntryRepo;

@Service
public class ProspectaServiceImpl implements ProspectaService {

	@Autowired
	private EntryRepo enRepo;
	
	@Override
	public void saveEntry(List<Entry> entries) {
	
		for(Entry en : entries) {
			enRepo.save(en);
		}
		
	}

}
