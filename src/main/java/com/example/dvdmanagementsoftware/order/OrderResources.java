package com.example.dvdmanagementsoftware.order;

import com.example.dvdmanagementsoftware.database.Database;
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
    public Response getOrders(){
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
    public Response getOrder(@PathParam("id") int id) {
        Order o = db.getOrder(id);
        return Response
                .status(Response.Status.OK)
                .entity(o)
                .build();
    }

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newOrder(String order) {
        try {
            JSONObject obj = new JSONObject(order);
            String address = obj.getString("address");
            int sCardId = obj.getInt("sCardId");
            int userId = obj.getInt("userId");
            String state = obj.getString("state");
            String creationDate = obj.getString("creationDate");
            String completedDate = obj.getString("completedDate");
            String dvdIds = obj.getString("dvdIds");
            Order nOrder = new Order(address, sCardId, userId, state, creationDate, completedDate, dvdIds);
            boolean status = db.newOrder(nOrder);
            if (status) return Response.ok("New shopping card created successfully!", MediaType.TEXT_HTML).build();
            return Response.ok("Shopping card creation failed!").build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.ok("Error: Something went wrong!", MediaType.TEXT_HTML).build();
    }

    @POST
    @Path("/{id}/state/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeState(@PathParam("id") int id, String state) {
        try {
            JSONObject obj = new JSONObject(state);
            String sstate = obj.getString("state");
            boolean status = db.changeStateOrder(id, sstate);
            if (status) return Response.ok("Order state updated successfully!", MediaType.TEXT_HTML).build();
            return Response.ok("Order state change failed!", MediaType.TEXT_HTML).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.ok("Error: Something went wrong!", MediaType.TEXT_HTML).build();
    }
}
