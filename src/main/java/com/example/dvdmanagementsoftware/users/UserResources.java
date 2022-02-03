package com.example.dvdmanagementsoftware.users;

import com.example.dvdmanagementsoftware.database.Database;
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
    public Response getUsers() {
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
    public Response getUser(@PathParam("id") int id) {
        User u = db.getUser(id);
        return Response
                .status(Response.Status.OK)
                .entity(u)
                .build();
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signUp(String user) {
        try {
            JSONObject obj = new JSONObject(user);
            String username = obj.getString("username");
            String password = obj.getString("password");
            String firstname = obj.getString("firstName");
            String lastname = obj.getString("lastName");
            int role = 1;
            String address = obj.getString("address");
            String cardType = obj.getString("cardType");
            String cardNumber = obj.getString("cardNumber");
            String cardExpirationNumber = obj.getString("cardExpirationDate");
            String cardCVV = obj.getString("cardCVV");
            User u = new User(username, password, firstname, lastname, role, address, cardType, cardNumber, cardExpirationNumber, cardCVV);
            boolean status = db.newUser(u);
            if (status) return Response.ok("Success : User registered successfully", MediaType.TEXT_HTML).build();
            return Response.ok("Error: Username already exists!", MediaType.TEXT_HTML).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.ok("Error: Something went wrong!", MediaType.TEXT_HTML).build();
    }

    @POST
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(String credentials) {
        try {
            JSONObject obj2 = new JSONObject(credentials);
            String username = obj2.getString("username");
            String password = obj2.getString("password");
            User user = db.signIn(username, password);
            if (user != null) return Response.ok("Signed in successfully!", MediaType.TEXT_HTML).build();
            return Response.ok("Error: Invalid password or username!", MediaType.TEXT_HTML).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.ok("Error: Something went wrong!", MediaType.TEXT_HTML).build();
    }

    @POST
    @Path("/{id}/updatePassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePassword(@PathParam("id") int id, String password) {
        try {
            JSONObject obj3 = new JSONObject(password);
            String pass = obj3.getString("password");
            boolean status = db.updatePassword(id, pass);
            if (status) return Response.ok("Password updated successfully!").build();
            return Response.ok("Error: Password update failed!").build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.ok("Error: Something went wrong!", MediaType.TEXT_HTML).build();
    }

    @POST
    @Path("/{id}/delete")
    public Response deleteUser(@PathParam("id") int id) {
        boolean status = db.deleteUser(id);
        if (status) return Response.ok("User deleted successfully!").build();
        return Response.ok("Error: User deletion failed!").build();
    }
}
