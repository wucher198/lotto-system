package pl.myjava.lotto.core.transformers;

import pl.myjava.lotto.api.LottoGame;
import pl.myjava.lotto.api.LottoNumber;
import pl.myjava.lotto.repository.entitis.LottoGameEntity;
import pl.myjava.lotto.repository.entitis.LottoNumberEntity;

public class Transformer {	
	public static LottoGame transform(LottoGameEntity entity) {
		return LottoGameTransformer.mapToDTO(entity);
	}
	
	public static LottoNumber transform(LottoNumberEntity entity) {
		return LottoNumberTransformer.mapToDTO(entity);
	}
	
	public static LottoNumberEntity transform(LottoNumber lottoNumber) {
		return LottoNumberTransformer.mapToBO(lottoNumber);
	}
	
	public static LottoGameEntity transform(LottoGame lottoGame) {
		return LottoGameTransformer.mapToBO(lottoGame);
	}
	
}
