package pl.myjava.lotto.web.controllers;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pl.myjava.lotto.core.LottoService;

@Path("/")
public class LottoController {
	@EJB
	private LottoService lottoService;

	@POST
	@Path("/example/json/{name}")
	@Produces("application/json")
	public String getHelloWorldJSON(@PathParam("name") String name) {
		System.out.print(name);
		return "{\"result\":\"" + lottoService.createHelloMessage(name) + "\"}";
	}
	
	@POST
	@Path("/example/xml/{name}")
	@Produces("application/xml")
	public String getHelloWorldXML(@PathParam("name") String name) {
		System.out.print(name);
		return "<xml><result>" + lottoService.createHelloMessage(name) + "</result></xml>";
	}
}
