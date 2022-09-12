package com.example.dvdmanagementsoftware.dvd;

import com.example.dvdmanagementsoftware.database.Database;
import com.example.dvdmanagementsoftware.messages.ErrorMessage;
import com.example.dvdmanagementsoftware.messages.SuccessMessage;
import com.example.dvdmanagementsoftware.users.Role;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/dvd")
public class DVDResources {
    Database db = new Database();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDVD(@PathParam("id") int id){
        DVD d = db.getDVD(id);
        return Response
                .status(Response.Status.OK)
                .entity(d)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newDVD(String dvd, @HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE, "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("User authentication failed!")).build();
        }
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
            if (status) return Response.status(Response.Status.OK).entity(new SuccessMessage("DVD registered successfully")).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("Something went wrong!")).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDVD(@PathParam("id") int id, @HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE, "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("User authentication failed!")).build();
        }
        boolean status = db.deleteDVD(id);
        if (status) {
            return Response.status(Response.Status.OK).entity(new SuccessMessage("DVD deleted successfully")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("DVD deletion failed")).build();
    }
}
