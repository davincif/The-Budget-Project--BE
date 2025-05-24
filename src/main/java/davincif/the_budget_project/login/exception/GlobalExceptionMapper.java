package davincif.the_budget_project.login.exception;

import davincif.the_budget_project.login.response.BaseErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        BaseErrorResponse<Void> response = new BaseErrorResponse<Void>()
            .setCode("500")
            .setFriendlyMessage("An unexpected error occurred.")
            .setTechnicalMessage(exception.getMessage());

        return Response.status(500).entity(response).build();
    }
}
