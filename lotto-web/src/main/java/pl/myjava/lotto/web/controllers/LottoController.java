package pl.myjava.lotto.web.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Optional;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.myjava.lotto.api.LottoGame;
import pl.myjava.lotto.api.LottoService;
import pl.myjava.lotto.core.LottoServiceImpl;

@Path("/")
public class LottoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LottoController.class);

	@EJB
	private LottoService lottoService;

	@POST
	@Path("/example/json/{name}")
	@Produces("application/json")
	public String getHelloWorldJSON(@PathParam("name") String name) {
		System.out.print(name);
		return "{\"result\":\"" + lottoService.test(name) + "\"}";
	}

	@POST
	@Path("/example/xml/{name}")
	@Produces("application/xml")
	public String getHelloWorldXML(@PathParam("name") String name) {
		System.out.print(name);
		return "<xml><result>" + lottoService.test(name) + "</result></xml>";
	}
	
	public String getResultForDate(@PathParam("date") String date, @PathParam("gameType") String gameType) {
		re		
	}

	public String addLottoGame(@PathParam("lottoGame") String lottoGame) {
		return lottoService.addLottoGame(fromJSON(lottoGame).get()).toString();
	}

	private Optional<LottoGame> fromJSON(String lottoGame) {
		Optional<LottoGame> result = Optional.empty();

		try (Reader reader = new StringReader(lottoGame)) {
			JsonReader jsonReader = Json.createReader(reader);
//			LottoGame
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			
		}

		return result;
	}
}
