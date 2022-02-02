package com.example.dvdmanagementsoftware.dvd;

import com.example.dvdmanagementsoftware.database.Database;

public class DVDResources {
    Database db = new Database();

//    @GET
//    @Path("/dvd")
//    @Produces(MediaType.APPLICATION_XML)
//    public ArrayList<DVD> getDVDS(){
//        return db.getDVDS();
//    }
//
//    @GET
//    @Path("/dvd/{id}")
//    @Produces(MediaType.APPLICATION_XML)
//    public DVD getDVD(@PathParam("id") int id){
//        return db.getDVD(id);
//    }
//
//    @GET
//    @Path("/users/{id}/dvd")
//    @Produces(MediaType.APPLICATION_XML)
//    public ArrayList<Order> getDVDOrder(@PathParam("id") int id){
//        return db.getDVDOrders(id);
//    }
//
//    @POST
//    @Path("/dvd/new")
//    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//    public DVD newDVD(DVD dvd) {
//        return db.newDVD(dvd);
//    }
//
//    @DELETE
//    @Path("/dvd/{id}/delete")
//    public Response deleteDVD(@PathParam("id") int id) {
//        boolean status = db.deleteDVD(id);
//        if(status) return Response.ok("DVD deleted successfully", MediaType.TEXT_HTML).build();
//        return Response.ok("Error: DVD deletion failed!", MediaType.TEXT_HTML).build();
//    }
}
