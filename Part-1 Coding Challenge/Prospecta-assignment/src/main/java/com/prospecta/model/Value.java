package com.prospecta.model;

import java.util.List;

import lombok.Data;

@Data
public class Value {

	private int count;
	private List<Entry> entries;
}
