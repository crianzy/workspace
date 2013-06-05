package com.czy.oa.dao.test;

import org.junit.Test;

public class TestDao {

	@Test
	public void testGetByIds(){
		long [] ids = {1,2,3,4};
		for (long id : ids) {
			System.out.print(id+"   ");
		}
		
		
	}
}
