package pl.myjava.lotto.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;

import pl.myjava.lotto.core.transformers.JsonCapable;

public class LottoGame implements JsonCapable {
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

	@Override
	public void createJson(JsonGenerator generator) {
		generator.writeStartObject("lottoGame").write("id", id);
			generator.writeStartArray("numbers");
				numbers.stream().forEach(number -> number.createJson(generator));
			generator.writeEnd();
		type.toJSON(generator);
		generator.writeEnd();
	}
	
	private static final String NAME = "lottoGame";
	private static final String ID = "id";
	private static final String TYPE = "type";
	private static final String NUMBERS = "numbers";
	
	public void parseJson(JsonObject object) {
		JsonValue thisObject = object.get(NAME);
		
		if (thisObject != null) {
			JsonObject obj = thisObject.asJsonObject();
			id = Long.valueOf(obj.getInt(ID));
			type = LottoGameType.parseJson(obj);
			JsonArray numbersArray = obj.getJsonArray(NUMBERS);
			
			if (numbersArray != null) {
				numbers = new ArrayList<>();
				
				for (Iterator<JsonValue> iter = numbersArray.iterator(); iter.hasNext();) {
					JsonObject numberObj = iter.next().asJsonObject();
					LottoNumber number = LottoNumber.builder().describedByJSON(numberObj).build();
					numbers.add(number);
				}
			}
		}		
	}

	public static class Builder {
		private LottoGame instance;

		public Builder() {
			instance = new LottoGame();
		}
		
		public Builder describedByJSON(JsonObject object) {
			instance.parseJson(object);
			
			return this;
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
}
