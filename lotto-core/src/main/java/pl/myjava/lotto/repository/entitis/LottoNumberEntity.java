package pl.myjava.lotto.repository.entitis;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "lotto_number")
public class LottoNumberEntity {
	@Id
	private Long id;
	private Byte number;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GAME_ID")
	private LottoGameEntity game;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Byte getNumber() {
		return number;
	}
	
	public void setNumber(Byte number) {
		this.number = number;
	}

	public LottoGameEntity getGame() {
		return game;
	}

	public void setGame(LottoGameEntity game) {
		this.game = game;
	}
}
