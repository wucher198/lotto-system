package pl.myjava.lotto.repository.entitis;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class LottoGameEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Long lotNumber;
	@OneToMany(mappedBy="game")
	private List<LottoNumberEntity> numbers;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<LottoNumberEntity> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<LottoNumberEntity> numbers) {
		this.numbers = numbers;
	}
	
	public Long getLotNumber() {
		return lotNumber;
	}
	
	public void setLotNumber(Long lotNumber) {
		this.lotNumber = lotNumber;
	}
}
