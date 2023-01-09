package com.prospecta.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prospecta.model.Entry;

public interface EntryRepo extends JpaRepository<Entry, Integer> {

}
