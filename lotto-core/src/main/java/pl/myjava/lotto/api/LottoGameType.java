package pl.myjava.lotto.api;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wucher198
 *
 */
public enum LottoGameType {	
	LOTTO("Lotto", 6, 6), MULTI("Multi Lotto", 10, 20), EUROJACKPOT("Euro JackPot", 10, 10);
	
	private static final Logger logger = LoggerFactory.getLogger(LottoGameType.class);
	private static Map<String, LottoGameType> byName;
	
	static {
		byName = Stream.of(LottoGameType.values()).collect(Collectors.toMap(LottoGameType::getName, value -> value));
	}

	private String name;
	private Integer minNumbers;
	private Integer maxNumbers;

	private LottoGameType(String name, Integer minNumbers, Integer maxNumbers) {
		this.name = name;
		this.minNumbers = minNumbers;
		this.maxNumbers = maxNumbers;
	}

	public String getName() {
		return name;
	}

	public Integer getMinNumbers() {
		return minNumbers;
	}

	public Integer getMaxNumbers() {
		return maxNumbers;
	}
	
	public static LottoGameType getByName(String name) {
		return byName.get(name);
	}
	
	private static final String TYPE_NAME = "lottoGameType";
	private static final String NAME = "name";
	private static final String MIN_NUMBERS = "minNumbers";
	private static final String MAX_NUMBERS = "maxNumbers";

	public void toJSON(JsonGenerator generator) {
		generator.writeStartObject(TYPE_NAME)
			.write(NAME, name)
			.write(MIN_NUMBERS, minNumbers)
			.write(MAX_NUMBERS, maxNumbers)
		.writeEnd();
	}
	
	public static LottoGameType parseJson(JsonObject object) {
		LottoGameType result = null;		
		JsonValue value = object.get(TYPE_NAME);
		
		if (value != null) {
			JsonObject obj = value.asJsonObject();
			result = LottoGameType.getByName(obj.getString(NAME));
			Integer min = obj.getInt(MIN_NUMBERS);
			Integer max = obj.getInt(MAX_NUMBERS);
			
			if (result.getMinNumbers() != min || result.getMaxNumbers() != max) {
				logger.warn("Min and Max value for game type: " + result.getName() + " is different!!!");
			}			
		}		
		
		return result;
	}
}
