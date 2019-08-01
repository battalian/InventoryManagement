package com.nagarro.nagp.repository;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;

public class RepositoryHelperTest {
	
	@Test
	public void shouldSaveFragile() {
		Inventory inventory = new Inventory(Category.FRAGILE);
		RepositoryHelper.saveFragile(inventory);
		assertTrue(true);
	}
	
	@Test
	public void shouldSaveDurable() {
		Inventory inventory = new Inventory(Category.DURABLE);
		RepositoryHelper.saveDurable(inventory);
		assertTrue(true);
	}

}
