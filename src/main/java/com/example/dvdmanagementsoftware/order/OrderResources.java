package com.example.dvdmanagementsoftware.order;

import com.example.dvdmanagementsoftware.database.Database;

import java.util.ArrayList;

public class OrderResources {
    ArrayList<Integer> dvdIds = new ArrayList<Integer>();
    Database db = new Database();

//    @GET
//    @Path("/orders")
//    @Produces(MediaType.APPLICATION_XML)
//    public ArrayList<Order> getOrders(){
//        return db.getOrders();
//    }
//
//    @GET
//    @Path("/orders/{id}")
//    @Produces(MediaType.APPLICATION_XML)
//    public Order getOrder(@PathParam("id") int id) {
//        return db.getOrder(id);
//    }
//
//    @POST
//    @Path("/orders/new")
//    @Consumes(MediaType.APPLICATION_XML)
//    public Response newOrder(Order order) {
//        boolean status = db.newOrder(order);
//        if(status) return Response.ok("Successfully created new order!", MediaType.TEXT_HTML).build();
//        return Response.ok("Error: Creation of a new order failed!", MediaType.TEXT_HTML).build();
//    }
//
//    @GET
//    @Path("/order/{id}/setState/{state}")
//    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//    public Response changeState(@PathParam("id") int id, @PathParam("state") String state) {
//        boolean status = db.setOrderState(id, state);
//        if(status) return Response.ok("State of order changed to : " + state).build();
//        return Response.ok("Error: Updating state failed").build();
//    }
}
