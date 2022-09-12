package com.example.dvdmanagementsoftware;

import com.example.dvdmanagementsoftware.database.Database;
import com.example.dvdmanagementsoftware.errors.Error;
import com.example.dvdmanagementsoftware.errors.Message;
import com.example.dvdmanagementsoftware.users.Role;
import com.example.dvdmanagementsoftware.users.User;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class DVDManagementSoftwareResources {
    Database db = new Database();
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
            String address = obj.getString("address");
            String cardType = obj.getString("cardType");
            String cardNumber = obj.getString("cardNumber");
            String cardExpirationNumber = obj.getString("cardExpirationDate");
            String cardCVV = obj.getString("cardCVV");
            User u = new User(username, password, firstname, lastname, String.valueOf(Role.CUSTOMER), address, cardType, cardNumber, cardExpirationNumber, cardCVV, "");
            boolean status = db.newUser(u);
            if (status) Response.status(Response.Status.OK).entity(new Message("Success : User registered successfully")).build();
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("Username already exists!")).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new Error("Error: Something went wrong!")).build();
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
            if (user != null) {
                JSONObject token = new JSONObject();
                token.put("token", user.getToken());
                String json = token.toString();
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Response.status(Response.Status.NOT_FOUND).entity(new Error("User not found!")).build();
    }

}
