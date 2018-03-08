package pl.myjava.lotto.core;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.myjava.lotto.api.LottoGame;
import pl.myjava.lotto.api.LottoService;
import pl.myjava.lotto.core.transformers.Transformer;
import pl.myjava.lotto.repository.LottoDAO;

@Stateless
public class LottoServiceImpl implements LottoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LottoServiceImpl.class);
	
	@EJB
	private LottoDAO lottoRepository;	
	
	public String test(String name) {
		LOGGER.info("Name: " + name);
		return "Hello you one little F*^%^%$^&^* " + name + "!!!!!!!!!";
	}
	
	public Long addLottoGame(LottoGame lottoGame) {
		return lottoRepository.save(Transformer.transform(lottoGame));
	}
}
