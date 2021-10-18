package com.memo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemoApplicationTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void contextLoads() {
		
	}
	
	@Test
	void 더하기테스트() {
		logger.debug("#@!#!@# 더하기테스트!@#!@#");
	}

}
