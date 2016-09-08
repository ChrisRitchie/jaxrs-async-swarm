package com.ritchie.resource;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Resource
    ManagedExecutorService managedExecutorService;
	
	@GET
	public UserVewModel getUserSync() {
		return getUser();
	}

	@GET
	@Asynchronous
	public UserVewModel getUserAsync(@Suspended final AsyncResponse asyncResponse) {
		return new UserVewModel(1, "Chris", "Ritchie");
	}

	/**
	 * The parameter javax.ws.rs.container.AsyncResponse is similar to the
	 * Servlet 3.0 javax.servlet.AsyncContext class and allows asynchronous
	 * request execution. In the above example, the request is suspended for the
	 * processing duration and the response is pushed to the client with the
	 * invocation of the method AsyncResponse#resume. The method roast is still
	 * executed synchronously, so the asynchronous execution does not bring any
	 * asynchronous behavior at all. However, the combination of
	 * EJB's @javax.ejb.Asynchronous annotation and the @Suspended AsyncResponse
	 * enables asynchronous execution of business logic with eventual
	 * notification of the interested client. Any JAX-RS root resource can be
	 * annotated with @Stateless or @Singleton annotations and can, in effect,
	 * function as an EJB (see Listing 4):
	 */
	@GET
	@Path("async")
	@Asynchronous
	public void asyncRestMethod(@Suspended final AsyncResponse asyncResponse) {
		
		asyncResponse.setTimeout(5, TimeUnit.MILLISECONDS);
		
		managedExecutorService.submit(() -> {
            sleep();
        });

		UserVewModel result = getUser();
		asyncResponse.resume(result);
	}

	private void sleep() {
		try {
			Thread.sleep(4000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private UserVewModel getUser() {
		return new UserVewModel(1, "Chris", "Ritchie");
	}

}
