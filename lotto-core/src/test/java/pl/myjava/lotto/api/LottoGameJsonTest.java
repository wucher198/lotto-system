package pl.myjava.lotto.api;

import java.io.StringReader;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.stream.JsonGenerator;

import org.testng.annotations.Test;

public class LottoGameJsonTest {
	private final String JSON = "{\"lottoGame\":{"
			+ "\"id\":1,"
			+ "\"numbers\":["
				+ "{\"id\":1,"
			 	+  "\"number\":12},"
			 	+ "{\"id\":2,\"number\":49}],\"lottoGameType\":{\"name\":\"Lotto\",\"minNumbers\":6,\"maxNumbers\":6}}}";

	@Test
	public void shouldCreateJson() {
		LottoGame game = LottoGame.builder().identifeidBy(1l).identifiedByType(LottoGameType.LOTTO)
				.addNumber(LottoNumber.builder().identifiedById(1l).withNumber((byte) 12).build())
				.addNumber(LottoNumber.builder().identifiedById(2l).withNumber((byte) 49).build()).build();
		StringWriter writer = new StringWriter();
		JsonGenerator generator = Json.createGenerator(writer);
		generator.writeStartObject();
		game.createJson(generator);
		generator.writeEnd();
		generator.flush();
		generator.close();
	}
	
	@Test
	public void shouldCreateObjectFromJson() {
		JsonReader reader = Json.createReader(new StringReader(JSON));
		LottoGame game = LottoGame.builder().describedByJSON(reader.readObject()).build();
		System.out.println(game);
	}
}
