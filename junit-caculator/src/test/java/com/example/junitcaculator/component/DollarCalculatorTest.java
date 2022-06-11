package com.example.junitcaculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * 모든 등록된 빈을 초기화 시키고 테스트
 * 특정 빈 사용을 지정 시 @Import 어노테이션 사용
 */
@SpringBootTest
public class DollarCalculatorTest {
	@MockBean
	private MarketApi marketApi;
	
	@Autowired
	private DollarCalculator dollarCalculator;
	
	@Test
	public void dollarCalculatorTest() {
		Mockito.when(marketApi.connect()).thenReturn(3000);
		dollarCalculator.init();
		
		int sum = dollarCalculator.sum(10, 10);
		int minus = dollarCalculator.minus(10, 10);
		
		Assertions.assertEquals(60000, sum);
		Assertions.assertEquals(0, minus);
	}
}
