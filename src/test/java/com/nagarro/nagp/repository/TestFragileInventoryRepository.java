package com.nagarro.nagp.repository;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RepositoryHelper.class)
public class TestFragileInventoryRepository {

	@Test
	public void shouldSaveFragileInventoryRepository() throws Exception {
		PowerMockito.mockStatic(RepositoryHelper.class);
		PowerMockito.doNothing().when(RepositoryHelper.class, "saveFragile", Mockito.any(String.class));
		Inventory inv = new Inventory(Category.FRAGILE);
		
		FragileInventoryRepository fragileInvRepo = new FragileInventoryRepository();
		fragileInvRepo.save(inv);
		
		PowerMockito.verifyStatic(RepositoryHelper.class);
		RepositoryHelper.saveFragile(Mockito.any(Inventory.class));
	}

}
