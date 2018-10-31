package pl.myjava.lotto.repository.entitis;

import javax.persistence.AttributeConverter;

import pl.myjava.lotto.api.LottoGameType;

public class LottoGameTypeConverter implements AttributeConverter<LottoGameType, Integer> {
	@Override
	public Integer convertToDatabaseColumn(LottoGameType attribute) {
		return attribute.ordinal();
	}

	@Override
	public LottoGameType convertToEntityAttribute(Integer dbData) {		
		return LottoGameType.values()[dbData];
	}

}
