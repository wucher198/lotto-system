package pl.myjava.lotto.repository.entitis;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LottoNumberEntity {
	@Id
	private Short id;
	private Integer number;
	
	public Short getId() {
		return id;
	}
	
	public void setId(Short id) {
		this.id = id;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}	
}
