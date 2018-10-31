package pl.myjava.lotto.api;

import java.util.List;

import javax.ejb.Local;

@Local
public interface LottoService {
	String test(String name);
	Long addLottoGame(LottoGame lottoGame);
	List<LottoGame> getAll();
	LottoGame getById(Long id);
}
