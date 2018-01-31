package pl.myjava.lotto.core;

import java.util.List;

public class LottoGame {
	private List<LottoNumber> numbers;

	public LottoGame(List<LottoNumber> numbers) {
		this.numbers = numbers;
	}

	public List<LottoNumber> getNumbers() {
		return numbers;
	}
	
	public static LottoGame with(List<LottoNumber> numbers) {
		return new LottoGame(numbers);
	}
}
