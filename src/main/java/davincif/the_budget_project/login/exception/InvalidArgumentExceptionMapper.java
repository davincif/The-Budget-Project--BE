package davincif.the_budget_project.login.exception;

import davincif.the_budget_project.login.response.BaseErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidArgumentExceptionMapper
    implements ExceptionMapper<InvalidArgumentException> {

    @Override
    public Response toResponse(InvalidArgumentException exception) {
        BaseErrorResponse<Void> response = new BaseErrorResponse<Void>()
            .setCode("400")
            .setFriendlyMessage(exception.getMessage());

        return Response.status(400).entity(response).build();
    }
}
