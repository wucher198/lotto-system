package pl.myjava.lotto.api;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

/**
 * @author wucher198
 *
 */
public enum LottoGameType {
	LOTTO("Lotto", 6, 6), MULTI("Multi Lotto", 10, 20), EUROJACKPOT("Euro JackPot", 10, 10);

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

	public void toJSON(JsonGenerator generator) {
		generator.writeStartObject("lottoGameType")
			.write("name", name)
			.write("minNumbers", minNumbers)
			.write("maxNumbers", maxNumbers)
		.writeEnd();
	}
}
