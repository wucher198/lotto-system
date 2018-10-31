package pl.myjava.lotto.core;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.myjava.lotto.api.LottoGame;
import pl.myjava.lotto.api.LottoService;
import pl.myjava.lotto.core.transformers.LottoGameTransformer;
import pl.myjava.lotto.core.transformers.Transformer;
import pl.myjava.lotto.core.transformers.TransformerFactory;
import pl.myjava.lotto.repository.LottoDAO;

@Stateless
public class LottoServiceImpl implements LottoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LottoServiceImpl.class);
	
	@EJB
	private LottoDAO repository;
	
	private LottoGameTransformer transformer = TransformerFactory.getLottoGameTransformer();
	
	public String test(String name) {
		LOGGER.info("Name: " + name);
		return "Hello you one little F*^%^%$^&^* " + name + "!!!!!!!!!";
	}

	@Override
	public Long addLottoGame(LottoGame lottoGame) {
		return repository.save(transformer.toENTITY((lottoGame)));
	}

	@Override
	public List<LottoGame> getAll() {
		return transformer.toDTOs(repository.getAll());
	}

	@Override
	public LottoGame getById(Long id) {
		return transformer.toDTO(repository.getById(id));
	}
	
	
}
