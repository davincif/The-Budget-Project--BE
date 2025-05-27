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

import davincif.the_budget_project.login.exception.NotImplementedException;
import davincif.the_budget_project.login.exception.NotImplementedExceptionMapper;
import davincif.the_budget_project.login.request.LoginRequest;
import davincif.the_budget_project.login.response.LoginResponse;
import davincif.the_budget_project.login.service.UserService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        LoginResponse loginUser = this.userService.loginUser(loginRequest);

        return Response.ok(200).entity(loginUser).build();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(LoginRequest loginRequest) {
        this.userService.createUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            );

        return Response.status(201).build();
    }

    @PATCH
    @Path("/register")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRegistry() {
        this.userService.updateUser();

        return new NotImplementedExceptionMapper()
            .toResponse(new NotImplementedException());
    }
}
