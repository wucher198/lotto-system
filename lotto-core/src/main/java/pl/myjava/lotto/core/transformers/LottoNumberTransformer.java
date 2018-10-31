package pl.myjava.lotto.core.transformers;

import pl.myjava.lotto.api.LottoNumber;
import pl.myjava.lotto.repository.entitis.LottoNumberEntity;

public class LottoNumberTransformer extends Transformer<LottoNumber, LottoNumberEntity> {
	@Override
	public LottoNumber toDTO(LottoNumberEntity entity) {
		LottoNumber.Builder builder = LottoNumber.builder();
		builder.identifiedById(entity.getId());
		builder.withNumber(entity.getNumber());
		
		return builder.build();
	}

	@Override
	public LottoNumberEntity toENTITY(LottoNumber dto) {
		LottoNumberEntity entity = new LottoNumberEntity();
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		
		return entity;
	}
}
