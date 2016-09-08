package com.ritchie.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public class SimpleResource {

	@GET
	@Path("simple")
	public Response doGet() {
		return Response.ok().build();
	}

}
