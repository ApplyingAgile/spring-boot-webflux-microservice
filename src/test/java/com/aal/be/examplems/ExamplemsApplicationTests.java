package com.aal.be.examplems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"server.port=0", "spring.cloud.bus.enabled=false", "spring.cloud.discovery.enabled=false", "spring.cloud.consul.enabled=false", "spring.cloud.config.discovery.enabled=false"})
public class ExamplemsApplicationTests {

	@Test
	public void contextLoads() {
		assertEquals(1, 1);
	}

}
