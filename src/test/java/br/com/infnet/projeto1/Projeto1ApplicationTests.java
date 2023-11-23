package br.com.infnet.projeto1;

import br.com.infnet.projeto1.util.CurrencyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Projeto1ApplicationTests {
	@Autowired
	CurrencyUtil util;
	@Test
	void contextLoads() {
		util.getCotacao();
		assertEquals(12, 12);
	}

}
