package com.nagarro.nagp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.nagarro.nagp.domain.Category;
import com.nagarro.nagp.exception.InvalidRequestException;
import com.nagarro.nagp.handler.InventoryHandler;

@RunWith(MockitoJUnitRunner.class)
public class TestInventoryResource {
	
	@InjectMocks
	InventoryResource invResource;
	
	@Mock
	private InventoryHandler mockInvHandler;

	@Test
	public void shouldReturnFragileInventory() {
		Inventory inv = new Inventory(Category.FRAGILE);
		Mockito.when(mockInvHandler.createInventory(Mockito.any(Inventory.class))).thenReturn(inv);
		
		Inventory actualInventory = invResource.createInventory(inv);
		
		Mockito.verify(mockInvHandler, times(1)).createInventory(Mockito.any(Inventory.class));
		assertEquals(actualInventory.getCategory(), Category.FRAGILE);
	}
	
	@Test
	public void shouldReturnDurableInventory() {
		Inventory inv = new Inventory(Category.DURABLE);
		Mockito.when(mockInvHandler.createInventory(Mockito.any(Inventory.class))).thenReturn(inv);
		
		Inventory actualInventory = invResource.createInventory(inv);
		
		Mockito.verify(mockInvHandler, times(1)).createInventory(Mockito.any(Inventory.class));
		assertEquals(actualInventory.getCategory(), Category.DURABLE);
	}
	
	@Test(expected = InvalidRequestException.class)
	public void shouldReturnExceptionForNullInventories() {
		Inventory inv = null;
	
		invResource.createInventory(inv);
	}

}
