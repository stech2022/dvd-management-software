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
    public Response getUsers() throws Exception{
        List<User> users = db.getUsers();
        System.out.println("Info: Found "+users.size()+" user(s) on DB");
        return Response
                .status(Response.Status.OK)
                .entity(users)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id){
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
        JSONObject obj = new JSONObject(user);
        String username = obj.getString("username");
        String password = obj.getString("password");
        String firstname = obj.getString("firstName");
        String lastname = obj.getString("lastName");
        int role = 1;
        String address = obj.getString("address");
        String cardType = obj.getString("cardType");
        String cardNumber =obj.getString("cardNumber");
        String cardExpirationNumber = obj.getString("cardExpirationDate");
        String cardCVV = obj.getString("cardCVV");
        System.out.println(cardCVV);
        User u = new User(username, password, firstname, lastname, role, address, cardType, cardNumber, cardExpirationNumber, cardCVV);
        boolean status = db.newUser(u);
        if(status) Response.ok("Success : User registered successfully", MediaType.TEXT_HTML).build();
        return Response.ok("Error: Username already exists!",MediaType.TEXT_HTML).build();
    }

    @GET
    @Path("/signIn")
    @Produces(MediaType.APPLICATION_XML)
    public Response signIn(@PathParam("username") String username, @PathParam("password") String password) {
        User user = db.signIn(username, password);
        if(user != null) return Response.ok("Signed in successfully!", MediaType.TEXT_HTML).build();
        return Response.ok("Error: Invalid password or username!", MediaType.TEXT_HTML).build();
    }

    @GET
    @Path("/{id}/updatePassword?currPass={pass}&newPass={newPass}")
    @Produces(MediaType.APPLICATION_XML)
    public Response updatePassword(@PathParam("id") int id, @PathParam("pass") String pass, @PathParam("newPass") String newPass) {
        boolean status = db.updatePassword(id, pass, newPass);
        if(status) return Response.ok("Password updated successfully!").build();
        return Response.ok("Error: Password update failed!").build();
    }

    @GET
    @Path("/{id}/delete")
    @Consumes(MediaType.APPLICATION_XML)
    public Response deleteUser(@PathParam("id") int id) {
        boolean status = db.deleteUser(id);
        if(status) return Response.ok("User deleted successfully!").build();
        return Response.ok("Error: User deletion failed!").build();
    }
//
//    @GET
//    @Path("/users/{id}/activeOrders")
//    @Consumes(MediaType.APPLICATION_XML)
//    public Response active(@PathParam("id") int id) {
//        return Response.ok("Info: There are " + db.activeOrders(id) + " active orders!").build();
//    }
//
//    @GET
//    @Path("/users/{id}/cancelledOrders")
//    @Consumes(MediaType.APPLICATION_XML)
//    public Response cancelled(@PathParam("id") int id) {
//        return Response.ok("Info: There are " + db.cancelledOrders(id) + " canceled orders!").build();
//    }
//
//    @GET
//    @Path("/users/{id}/completedOrders")
//    @Consumes(MediaType.APPLICATION_XML)
//    public Response completed(@PathParam("id") int id) {
//        return Response.ok("Info: There are " + db.statsOrdersCompletedUser(id) + " completed orders!").build();
//    }
}
