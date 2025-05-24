package davincif.the_budget_project.login.exception;

import davincif.the_budget_project.login.response.BaseErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper
    implements ExceptionMapper<UserNotFoundException> {

    @Override
    public Response toResponse(UserNotFoundException exception) {
        BaseErrorResponse<Void> response = new BaseErrorResponse<Void>()
            .setCode("404")
            .setFriendlyMessage(exception.getMessage());

        return Response.status(404).entity(response).build();
    }
}
