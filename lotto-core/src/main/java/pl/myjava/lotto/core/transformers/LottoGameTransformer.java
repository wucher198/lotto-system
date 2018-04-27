package pl.myjava.lotto.core.transformers;

import java.util.stream.Collectors;

import pl.myjava.lotto.api.LottoGame;
import pl.myjava.lotto.repository.entitis.LottoGameEntity;

public class LottoGameTransformer {
	public static LottoGame mapToDTO(LottoGameEntity entity) {
		LottoGame.Builder builder = LottoGame.builder();
		builder.identifeidBy(entity.getId());
		builder.identifiedByName(entity.getName());
		entity.getNumbers().stream().map(Transformer::transform).forEach(number -> builder.addNumber(number));
		
		return builder.build();
	}
	
	public static LottoGameEntity mapToBO(LottoGame lottoGame) {
		LottoGameEntity entity = new LottoGameEntity();
		entity.setId(lottoGame.getId());
		entity.setLotNumber(lottoGame.getLotNumber());
		entity.setName(lottoGame.getName());
		entity.setNumbers(lottoGame.getNumbers().stream().map(Transformer::transform).collect(Collectors.toList()));
		
		return entity;
	}
}
