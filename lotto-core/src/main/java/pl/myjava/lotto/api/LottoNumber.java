package pl.myjava.lotto.api;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

public class LottoNumber {
	private Long id;
	private Byte number;

	public static Builder builder() {
		return new Builder();
	}

	public static JSON json() {
		return new JSON();
	}

	public Long getId() {
		return id;
	}

	public Byte getNumber() {
		return number;
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

	public static class JSON {
		public void write(LottoNumber number, JsonGenerator generator) {
			generator.writeStartObject("lottoNumber")
				.write("id", number.getId())
				.write("number", number.getNumber())
			.writeEnd();
		}
	}
}
