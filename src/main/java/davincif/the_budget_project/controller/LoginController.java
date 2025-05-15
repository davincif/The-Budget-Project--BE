package davincif.the_budget_project.controller;

import davincif.the_budget_project.Presenter.ErrorResponsePresenter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        return Response.status(501)
                .entity(ErrorResponsePresenter.notImplemented())
                .build();
    }

    @GET()
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register() {
        return Response.status(501)
                .entity(ErrorResponsePresenter.notImplemented())
                .build();
    }
}
