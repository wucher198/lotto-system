package pl.myjava.lotto.api;

import javax.ejb.Local;

@Local
public interface LottoService {
	String test(String name);
	Long addLottoGame(LottoGame lottoGame);
}
