package com.nagarro.nagp.handler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.nagarro.nagp.Inventory;
import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.repository.DurableInventoryRepository;
import com.nagarro.nagp.repository.FragileInventoryRepository;
import com.nagarro.nagp.exception.InvalidRequestException;


@RunWith(MockitoJUnitRunner.class)
public class TestInventoryHandler {
	
	@InjectMocks
	InventoryHandler invHandler;
	
	@Mock
	private FragileInventoryRepository fragileRepo;
	
	@Mock
	private DurableInventoryRepository durableRepo;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void shouldReturnFragileInventoryAfterSavingInFragileRepository() throws Exception {
		Inventory inventory = new Inventory(Category.FRAGILE);
		Mockito.doNothing().when(fragileRepo).save(Mockito.any(Inventory.class));
	
		Inventory actualInventory = invHandler.createInventory(inventory);
		
		Mockito.verify(fragileRepo, times(1)).save(Mockito.any(Inventory.class));
		Mockito.verify(durableRepo, times(0)).save(Mockito.any(Inventory.class));
		assertEquals(Category.FRAGILE, actualInventory.getCategory());
	}
	
	@Test
	public void shouldReturnFragileInventoryAfterSavingInDurableRepository() throws Exception {
		Inventory inventory = new Inventory(Category.DURABLE);
		Mockito.doNothing().when(durableRepo).save(Mockito.any(Inventory.class));
	
		Inventory actualInventory = invHandler.createInventory(inventory);
		
		Mockito.verify(fragileRepo, times(0)).save(Mockito.any(Inventory.class));
		Mockito.verify(durableRepo, times(1)).save(Mockito.any(Inventory.class));
		assertEquals(Category.FRAGILE, actualInventory.getCategory());
	}
	
	@Test/*(expected = InvalidRequestException.class)*/
	public void shouldReturnExceptionForOtherInventories() throws Exception {
		Inventory mockInventory = Mockito.mock(Inventory.class);
		Mockito.when(mockInventory.getCategory()).thenReturn(null);
		
		exceptionRule.expect(InvalidRequestException.class);
		exceptionRule.expectMessage("Wrong inventory type");		
		invHandler.createInventory(mockInventory);	
	}

}
