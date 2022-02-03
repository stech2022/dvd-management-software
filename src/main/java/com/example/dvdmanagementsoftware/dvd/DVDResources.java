package com.example.dvdmanagementsoftware.dvd;

import com.example.dvdmanagementsoftware.database.Database;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/dvd")
public class DVDResources {
    Database db = new Database();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDVDS(){
        List<DVD> dvds = db.getDVDS();
        System.out.println("Info: Found " + dvds.size() + " dvd(s) on DB");
        return Response
                .status(Response.Status.OK)
                .entity(dvds)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDVD(@PathParam("id") int id){
        DVD d = db.getDVD(id);
        return Response
                .status(Response.Status.OK)
                .entity(d)
                .build();
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newDVD(String dvd) {
        JSONObject obj = new JSONObject(dvd);
        try {
            String title = obj.getString("title");
            String actors = obj.getString("actors");
            String director = obj.getString("director");
            String produceDate = obj.getString("produceDate");
            int duration = obj.getInt("duration");
            String languages = obj.getString("languages");
            String subtitles = obj.getString("subtitles");
            String category = obj.getString("category");
            double price = obj.getDouble("price");
            int units = obj.getInt("units");
            DVD d = new DVD(title, actors, director, produceDate, duration, languages, subtitles, category, price, units);
            boolean status = db.newDVD(d);
            if (status) return Response.ok("Success : DVD registered successfully", MediaType.TEXT_HTML).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.ok("Error: Something went wrong!", MediaType.TEXT_HTML).build();
    }

    @POST
    @Path("/{id}/delete")
    public Response deleteDVD(@PathParam("id") int id) {
        boolean status = db.deleteDVD(id);
        if(status) return Response.ok("DVD deleted successfully", MediaType.TEXT_HTML).build();
        return Response.ok("Error: DVD deletion failed!", MediaType.TEXT_HTML).build();
    }
}
