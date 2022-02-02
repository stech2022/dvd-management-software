package com.example.dvdmanagementsoftware.shoppingcard;

import com.example.dvdmanagementsoftware.database.Database;

public class ShoppingCardResources {
    Database db = new Database();

//    @GET
//    @Path("/cards")
//    @Produces(MediaType.APPLICATION_XML)
//    public ArrayList<ShoppingCard> getShoppingCards(){
//        return db.getShoppingCards();
//    }
//
//    @GET
//    @Path("/cards/{id}")
//    @Produces(MediaType.APPLICATION_XML)
//    public ShoppingCard getShoppingCard(@PathParam("id") int id) {
//        return db.getShoppingCard(id);
//    }
//
//    @DELETE
//    @Path("/cards/{id}/delete")
//    @Consumes(MediaType.APPLICATION_XML)
//    public Response deleteShoppingCard(@PathParam("id") int id) {
//        boolean status = db.deleteShoppingCard(id);
//        if(status) return Response.ok("Shopping card deleted successfully!", MediaType.TEXT_HTML).build();
//        return Response.ok("Error: Shopping card deletion failed!", MediaType.TEXT_HTML).build();
//    }
//
//    @POST
//    @Path("/cards/new")
//    @Consumes(MediaType.APPLICATION_XML)
//    public Response newShoppingCard(ShoppingCard shoppingCard) {
//        boolean status = db.newShoppingCard(shoppingCard);
//        if(status) return Response.ok("New shopping card created successfully!", MediaType.TEXT_HTML).build();
//        return Response.ok("Shopping card creation failed!").build();
//    }
//
//    @PUT
//    @Path("/cards/{id}/state/{state}")
//    @Consumes(MediaType.APPLICATION_XML)
//    public Response changeState(@PathParam("id") int id, @PathParam("state") String state) {
//        boolean status = db.changeStateShoppingCard(id, state);
//        if(status) return Response.ok("Shopping card state updated successfully!", MediaType.TEXT_HTML).build();
//        return Response.ok("Shopping card state failed!", MediaType.TEXT_HTML).build();
//    }
//
//    @GET
//    @Path("/cards/user/{id}")
//    @Produces(MediaType.APPLICATION_XML)
//    public ArrayList<ShoppingCard> usersCards(@PathParam("id") int id){
//        return db.usersShoppingCard(id);
//    }
}
