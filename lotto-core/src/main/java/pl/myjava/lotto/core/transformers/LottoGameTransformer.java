package pl.myjava.lotto.core.transformers;

import pl.myjava.lotto.api.LottoGame;
import pl.myjava.lotto.repository.entitis.LottoGameEntity;

public class LottoGameTransformer extends Transformer<LottoGame, LottoGameEntity>{
	@Override
	public LottoGame toDTO(LottoGameEntity entity) {
		LottoGame.Builder builder = LottoGame.builder();
		builder.identifeidBy(entity.getId());
		builder.identifiedByType(entity.getType());
		builder.withNumbers(TransformerFactory.getLottoNumberTransformer().toDTOs(entity.getNumbers()));
		
		return builder.build();
	}

	@Override
	public LottoGameEntity toENTITY(LottoGame dto) {
		LottoGameEntity entity = new LottoGameEntity();
		entity.setId(dto.getId());
		entity.setType(dto.getType());
		entity.setNumbers(TransformerFactory.getLottoNumberTransformer().toENTITYs(dto.getNumbers()));
		
		return entity;
	}
}
