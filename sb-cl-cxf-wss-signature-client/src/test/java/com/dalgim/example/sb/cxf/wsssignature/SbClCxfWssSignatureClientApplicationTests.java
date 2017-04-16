package com.dalgim.example.sb.cxf.wsssignature;

import com.dalgim.example.sb.cxf.wsssignature.endpoint.FruitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbClCxfWssSignatureClientApplicationTests {

	@Autowired
	private FruitService fruitService;

	@Test
	public void contextLoads() {
		fruitService.getAllFruit();
	}

}
