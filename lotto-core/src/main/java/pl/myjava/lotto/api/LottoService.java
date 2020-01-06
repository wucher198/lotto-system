package pl.myjava.lotto.api;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Local;

@Local
public interface LottoService {
	String test(String name);
	LottoGame readResultForDate(LocalDate gameDate, LottoGameType gameType);
	Long addLottoGame(LottoGame lottoGame);
	List<LottoGame> getAll(); 
	LottoGame getById(Long id);
}
