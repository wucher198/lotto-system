package pl.myjava.lotto.core.transformers;

import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;

public interface JsonCapable {
	void createJson(JsonGenerator generator);
	void parseJson(JsonObject generator);
}
