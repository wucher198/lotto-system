package pl.myjava.lotto.api;

import java.time.LocalDate;

import javax.json.JsonObject;

public class QueryParameters {
	private LocalDate gameDate;
	private LottoGameType gameType;
		
	public static QueryParameters of(LocalDate gameDate, LottoGameType gameType) {
		return new QueryParameters(gameDate, gameType);
	}
	
	private QueryParameters() {
		
	}
	
	private QueryParameters(LocalDate gameDate, LottoGameType gameType) {
		this.gameDate = gameDate;
		this.gameType = gameType;
	}
	
	public LocalDate getGameDate() {
		return gameDate;
	}
	
	public LottoGameType getGameType() {
		return gameType;
	}
	
	public static class JSON {
		public static final String GAME_DATE = "gameDate";
		public static final String GAME_TYPE = "gameType";
		
		public QueryParameters parse(JsonObject object) {
			QueryParameters result = new QueryParameters();
			
			ch
			
			return result;
		}
	}
}
