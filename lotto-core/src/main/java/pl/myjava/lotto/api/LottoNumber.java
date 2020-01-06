package pl.myjava.lotto.api;

import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;

import pl.myjava.lotto.core.transformers.JsonCapable;

public class LottoNumber implements JsonCapable {
	private Long id;
	private Byte number;

	public static Builder builder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public Byte getNumber() {
		return number;
	}
	
	private static final String TYPE_NAME = "lottoNumber";
	private static final String ID = "id";
	private static final String NUMBER = "number";

	@Override
	public void createJson(JsonGenerator generator) {
		generator
			.writeStartObject()
				.write(ID, id)
				.write(NUMBER, number)
			.writeEnd();
	}
	
	public void parseJson(JsonObject object) {		
		if (object != null) {
			id = Long.valueOf(object.getInt(ID));
			number = (byte) object.getInt(NUMBER);			
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		LottoNumber other = (LottoNumber) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LottoNumber [number=" + number + "]";
	}

	public static class Builder {
		private LottoNumber instance;

		private Builder() {
			instance = new LottoNumber();
		}
		
		public Builder describedByJSON(JsonObject object) {
			instance.parseJson(object);
			
			return this;
		}

		public Builder identifiedById(Long id) {
			instance.id = id;

			return this;
		}

		public Builder withNumber(Byte number) {
			instance.number = number;

			return this;
		}

		public LottoNumber build() {
			return instance;
		}
	}
}
