package davincif.the_budget_project.login.exception;

import davincif.the_budget_project.login.response.BaseErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserAlreadyExistsExceptionMapper
    implements ExceptionMapper<UserAlreadyExistsException> {

    @Override
    public Response toResponse(UserAlreadyExistsException exception) {
        BaseErrorResponse<Void> response = new BaseErrorResponse<Void>()
            .setCode("409")
            .setFriendlyMessage(exception.getMessage());

        return Response.status(409).entity(response).build();
    }
}
