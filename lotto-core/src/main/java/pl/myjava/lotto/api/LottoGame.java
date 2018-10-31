package pl.myjava.lotto.api;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class LottoGame {
	private Long id;
	private LottoGameType type;
	private List<LottoNumber> numbers;
	
	public static Builder builder() {
		return new Builder();
	}
	
	private LottoGame() {
		numbers = new ArrayList<>();
	}
	
	public LottoGame(Long id, LottoGameType type, List<LottoNumber> numbers) {
		this.id = id;
		this.type = type;
		this.numbers = numbers;
	}
	
	public Long getId() {
		return id;
	}

	public LottoGameType getType() {
		return type;
	}

	public List<LottoNumber> getNumbers() {
		return numbers;
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
		
		public Builder withNumbers(List<LottoNumber> numbers) {
			instance.numbers.addAll(numbers);
			
			return this;
		}
		
		public Builder identifiedByType(LottoGameType type) {
			instance.type = type;
			
			return this;
		}
		
		public LottoGame build() {
			return instance;
		}
	}
	
	public static class JSON {
		public String create(LottoGame lottoGame) {
			StringWriter writer = new StringWriter();
			JsonGenerator generator = Json.createGenerator(writer);
			generator.writeStartObject("lottoGame")
				.write("id", lottoGame.getId());
			lottoGame.getType().toJSON(generator);
			generator.writeStartArray("numbers");
			lottoGame.getNumbers().stream()
				.forEach(number -> LottoNumber.json().write(number, generator));
			generator
				.writeEnd()
				.close();
			
			return writer.toString();
		}
	}
}
