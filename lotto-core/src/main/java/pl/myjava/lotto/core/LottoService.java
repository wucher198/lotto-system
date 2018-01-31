package pl.myjava.lotto.core;

import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class LottoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LottoService.class);
	
	public String createHelloMessage(String name) {
		LOGGER.info("Name: " + name);
		return "Hello you one little F*^%^%$^&^* " + name + "!!!!!!!!!";
	}
}
