package com.my.springboot.springboot1;

import com.my.springboot.springboot1.async.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private Task task;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testAsyncTask() throws InterruptedException {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
		Thread.currentThread().join();
	}

}
