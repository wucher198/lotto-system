package pl.myjava.lotto.core.transformers;

import pl.myjava.lotto.api.LottoNumber;
import pl.myjava.lotto.repository.entitis.LottoNumberEntity;

public class LottoNumberTransformer {
	public static LottoNumber mapToDTO(LottoNumberEntity lottoNumber) {
		LottoNumber.Builder builder = LottoNumber.builder();
		builder.identifiedById(lottoNumber.getId());
		builder.withNumber(lottoNumber.getNumber());
		
		return builder.build();
	}
	
	public static LottoNumberEntity mapToBO(LottoNumber lottoNumber) {
		LottoNumberEntity entity = new LottoNumberEntity();
		entity.setId(lottoNumber.getId());
		entity.setNumber(lottoNumber.getNumber());
		
		return entity;
	}
}
