package com.nagarro.nagp.repository;

import static org.mockito.Mockito.times;

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
public class TestDurableInventoryRepository {

	@Test
	public void shouldSaveDurableInventoryRepository() throws Exception {
		PowerMockito.mockStatic(RepositoryHelper.class);
		PowerMockito.doNothing().when(RepositoryHelper.class, "saveDurable", Mockito.any(String.class));
		Inventory inv = new Inventory(Category.DURABLE);
		
		DurableInventoryRepository durableInvRepo = new DurableInventoryRepository();
		durableInvRepo.save(inv);
		
		PowerMockito.verifyStatic(RepositoryHelper.class);
		RepositoryHelper.saveDurable(Mockito.any(Inventory.class));
	}

}
