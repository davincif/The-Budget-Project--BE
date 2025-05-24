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

package davincif.the_budget_project.login.controller;

import davincif.the_budget_project.login.dto.UserDTO;
import davincif.the_budget_project.login.dto.valueObject.Token;
import davincif.the_budget_project.login.request.LoginRequest;
import davincif.the_budget_project.login.response.BaseErrorResponse;
import davincif.the_budget_project.login.response.InternalErrorResponse;
import davincif.the_budget_project.login.response.NotImplementedErrorResponse;
import davincif.the_budget_project.login.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Optional;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        Optional<UserDTO> existentUser;

        try {
            existentUser = this.userService.searchUser(loginRequest.getEmail());
        } catch (IllegalArgumentException e) {
            // TODO: ENHANCE ERROR HANDLER
            return this.illegalArgumentResponse(e.getMessage());
        }

        if (existentUser.isEmpty()) {
            return this.userNotFoundResponse();
        }

        Token token = this.userService.generateLoginToken(existentUser.get());

        return Response.ok().build();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(LoginRequest loginRequest) {
        Optional<UserDTO> existentUser;

        try {
            existentUser = this.userService.searchUser(loginRequest.getEmail());
        } catch (IllegalArgumentException e) {
            // TODO: ENHANCE ERROR HANDLER
            return this.illegalArgumentResponse(e.getMessage());
        }

        if (existentUser.isPresent()) {
            return this.alreadyExistentUserResponse();
        }

        try {
            this.userService.craeteUser(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                );
        } catch (Exception error) {
            return this.internalServerErrorResponse(error.getMessage());
        }

        return Response.status(201).build();
    }

    private Response userNotFoundResponse() {
        BaseErrorResponse<Void> response = new BaseErrorResponse<Void>()
            .setCode("404")
            .setFriendlyMessage("This user don't exist. Try registering");

        return Response.status(404).entity(response).build();
    }

    private Response alreadyExistentUserResponse() {
        BaseErrorResponse<Void> response = new BaseErrorResponse<Void>()
            .setCode("409")
            .setFriendlyMessage("This user already exists. Try logging in");

        return Response.status(409).entity(response).build();
    }

    private Response illegalArgumentResponse(String message) {
        BaseErrorResponse<Void> response = new BaseErrorResponse<Void>()
            .setCode("400")
            .setFriendlyMessage(message);

        return Response.status(409).entity(response).build();
    }

    private Response notImplementedReponse() {
        NotImplementedErrorResponse response =
            new NotImplementedErrorResponse();

        return Response.status(501).entity(response).build();
    }

    private Response internalServerErrorResponse(String message) {
        InternalErrorResponse response = new InternalErrorResponse(message);

        return Response.status(500).entity(response).build();
    }
}
