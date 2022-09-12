package com.example.dvdmanagementsoftware.order;

import com.example.dvdmanagementsoftware.database.Database;
import com.example.dvdmanagementsoftware.errors.Error;
import com.example.dvdmanagementsoftware.errors.Message;
import com.example.dvdmanagementsoftware.users.Role;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/orders")
public class OrderResources {
    Database db = new Database();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(@HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE, "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("User authentication failed!")).build();
        }
        List<Order> orders = db.getOrders();
        System.out.println("Info: Found " + orders.size() + " order(s) on DB");
        return Response
                .status(Response.Status.OK)
                .entity(orders)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") int id, @HeaderParam("token") String givenToken) {
        Order o = db.getOrder(id);
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE + "/" + Role.CUSTOMER, o.getUserId()+"");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("User authentication failed!")).build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(o)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response newOrder(String order, @HeaderParam("token") String givenToken) {
        try {
            JSONObject obj = new JSONObject(order);
            String address = obj.getString("address");
            int sCardId = obj.getInt("sCardId");
            int userId = obj.getInt("userId");
            boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE + "/" + Role.CUSTOMER, ""+userId);
            if (!isAuthenticated) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Error("User authentication failed!")).build();
            }
            String state = obj.getString("state");
            String creationDate = obj.getString("creationDate");
            String completedDate = obj.getString("completedDate");
            String dvdIds = obj.getString("dvdIds");
            Order nOrder = new Order(address, sCardId, userId, state, creationDate, completedDate, dvdIds);
            boolean status = db.newOrder(nOrder);
            if (status) return Response.status(Response.Status.OK).entity(new Message("New order created successfully!")).build();
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("Shopping card creation failed!")).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new Error("Error: Something went wrong!")).build();
    }

    @POST
    @Path("/{id}/state/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeState(@PathParam("id") int id, String state, @HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE, "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("User authentication failed!")).build();
        }
        try {
            JSONObject obj = new JSONObject(state);
            String sstate = obj.getString("state");
            boolean status = db.changeStateOrder(id, sstate);
            if (status) return Response.status(Response.Status.OK).entity(new Message("Order state changed successfully!")).build();
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("Order state change failed!")).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new Error("Error: Something went wrong!")).build();
    }
}
