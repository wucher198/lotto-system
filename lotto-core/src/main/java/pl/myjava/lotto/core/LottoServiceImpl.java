package pl.myjava.lotto.core;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.myjava.lotto.api.LottoGame;
import pl.myjava.lotto.api.LottoGameType;
import pl.myjava.lotto.api.LottoNumber;
import pl.myjava.lotto.api.LottoService;
import pl.myjava.lotto.core.transformers.LottoGameTransformer;
import pl.myjava.lotto.core.transformers.TransformerFactory;
import pl.myjava.lotto.repository.LottoDAO;

@Stateless
public class LottoServiceImpl implements LottoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LottoService.class);
	
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

	@Override
	public LottoGame readResultForDate(LocalDate date, LottoGameType gameType) {
		LOGGER.info("readResultForDate(" + date.toString() + ", " + gameType.toString() + ")");
		List<LottoNumber> numbers = LongStream.range(1, 6).mapToObj(number -> LottoNumber.builder().identifiedById(number).withNumber((byte) number).build()).collect(Collectors.toList());		
		LottoGame result = new LottoGame(1l, gameType, numbers);
		
		return result;
	}
	
	
}
