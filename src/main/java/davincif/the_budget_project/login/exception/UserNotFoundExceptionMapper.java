/*
Copyright 2025 Leonardo Da Vinci Feliciano Sebasitão

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License
*/

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
