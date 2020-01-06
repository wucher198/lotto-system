package pl.myjava.lotto.repository.entitis;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import pl.myjava.lotto.api.LottoGameType;

@Entity(name = "lotto_game")
public class LottoGameEntity {
	@Id
	@GeneratedValue
	private Long id;
	@Convert(converter = LottoGameTypeConverter.class)
	private LottoGameType type;
	@OneToMany(mappedBy="game")
	private List<LottoNumberEntity> numbers;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LottoGameType getType() {
		return type;
	}
	
	public void setType(LottoGameType type) {
		this.type = type;
	}

	public List<LottoNumberEntity> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<LottoNumberEntity> numbers) {
		this.numbers = numbers;
	}
}
