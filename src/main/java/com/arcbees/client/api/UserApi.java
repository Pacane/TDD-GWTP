package com.arcbees.client.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path(ApiPaths.USERS)
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public interface UserApi {
    @GET
    List<User> getUsers();

    @DELETE
    @Path(ApiPaths.PATH_ID)
    List<User> delete(@PathParam(ApiParameters.ID) int userId);

    @GET
    @Path(ApiPaths.PATH_ID)
    User getUser(@PathParam(ApiParameters.ID) int userId);

    @PUT
    @Path(ApiPaths.PATH_ID)
    void saveUser(@PathParam(ApiParameters.ID) int userId, User user);
}
