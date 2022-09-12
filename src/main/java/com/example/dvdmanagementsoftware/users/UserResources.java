package com.example.dvdmanagementsoftware.users;

import com.example.dvdmanagementsoftware.database.Database;
import com.example.dvdmanagementsoftware.errors.Error;
import com.example.dvdmanagementsoftware.errors.Message;
import org.json.JSONObject;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResources {
    Database db = new Database();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE, "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("User authentication failed!")).build();
        }
        List<User> users = db.getUsers();
        System.out.println("Info: Found " + users.size() + " user(s) on DB");
        return Response
                .status(Response.Status.OK)
                .entity(users)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id, @HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE + "/" + Role.CUSTOMER, id+"");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("User authentication failed!")).build();
        }
        User u = db.getUser(id);
        return Response
                .status(Response.Status.OK)
                .entity(u)
                .build();
    }

    @POST
    @Path("/{id}/updatePassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePassword(@PathParam("id") int id, String password, @HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, "" + Role.CUSTOMER, id + "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("User authentication failed!")).build();
        }
        try {
            JSONObject obj3 = new JSONObject(password);
            String pass = obj3.getString("password");
            boolean status = db.updatePassword(id, pass);
            if (status) return Response.ok(new Message("Password updated successfully!")).build();
            return Response.ok(new Error("Password update failed!")).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new Error("Something went wrong!")).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id, @HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE + "/" + Role.CUSTOMER, id+"");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("User authentication failed!")).build();
        }
        boolean status = db.deleteUser(id);
        String msg = "User deletion failed";
        Response.Status status1 = Response.Status.NOT_FOUND;
        if (status) {
            msg = "User deleted successfully";
            status1 = Response.Status.OK;
        }
        return Response.status(status1).entity(msg).build();
    }
}
