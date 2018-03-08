package pl.myjava.lotto.api;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {
	private Long id;
	private String name;
	private Long lotNumber;
	private List<LottoNumber> numbers;
	
	public static Builder builder() {
		return new Builder();
	}
	
	private LottoGame() {
		numbers = new ArrayList<>();
	}
	
	public LottoGame(Long id, String name, List<LottoNumber> numbers) {
		this.id = id;
		this.name = name;
		this.numbers = numbers;
	}	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<LottoNumber> getNumbers() {
		return numbers;
	}
	
	public Long getLotNumber() {
		return lotNumber;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lotNumber == null) ? 0 : lotNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numbers == null) ? 0 : numbers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LottoGame other = (LottoGame) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lotNumber == null) {
			if (other.lotNumber != null)
				return false;
		} else if (!lotNumber.equals(other.lotNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numbers == null) {
			if (other.numbers != null)
				return false;
		} else if (!numbers.equals(other.numbers))
			return false;
		return true;
	}



	public static class Builder {
		private LottoGame instance;
		
		public Builder() {
			instance = new LottoGame();
		}
		
		public Builder identifeidBy(Long id) {
			instance.id = id;
			
			return this;
		}
		
		public Builder addNumber(LottoNumber number) {
			instance.numbers.add(number);
			
			return this;
		}
		
		public Builder identifiedByName(String name) {
			instance.name = name;
			
			return this;
		}
		
		public LottoGame build() {
			return instance;
		}
	}
}
