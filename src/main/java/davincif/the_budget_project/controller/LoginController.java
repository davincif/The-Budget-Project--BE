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

package davincif.the_budget_project.controller;

import davincif.the_budget_project.Presenter.ErrorResponsePresenter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        return Response.status(501)
                .entity(ErrorResponsePresenter.notImplemented())
                .build();
    }

    @POST()
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register() {
        return Response.status(501)
                .entity(ErrorResponsePresenter.notImplemented())
                .build();
    }
}
