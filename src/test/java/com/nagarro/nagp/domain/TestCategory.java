package com.nagarro.nagp.domain;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class TestCategory {

	@Test
	public void shouldCategory() {
		assertEquals(Category.valueOf("DURABLE"), Category.DURABLE);
		assertEquals(Category.DURABLE, Category.DURABLE);
		assertTrue(true);
	}

}
