package com.example.dvdmanagementsoftware.shoppingcard;

import com.example.dvdmanagementsoftware.database.Database;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cards")
public class ShoppingCardResources {
    Database db = new Database();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingCards(){
        List<ShoppingCard> sCards = db.getShoppingCards();
        System.out.println("Info: Found " + sCards.size() + " shopping card(s) on DB");
        return Response
                .status(Response.Status.OK)
                .entity(sCards)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingCard(@PathParam("id") int id) {
        ShoppingCard s = db.getShoppingCard(id);
        return Response
                .status(Response.Status.OK)
                .entity(s)
                .build();
    }

    @POST
    @Path("/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteShoppingCard(@PathParam("id") int id) {
        boolean status = db.deleteShoppingCard(id);
        if(status) return Response.ok("Shopping card deleted successfully!", MediaType.TEXT_HTML).build();
        return Response.ok("Error: Shopping card deletion failed!", MediaType.TEXT_HTML).build();
    }

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newShoppingCard(ShoppingCard shoppingCard) {
        try {
            boolean status = db.newShoppingCard(shoppingCard);
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
            boolean status = db.changeStateShoppingCard(id, sstate);
            if (status) return Response.ok("Shopping card state updated successfully!", MediaType.TEXT_HTML).build();
            return Response.ok("Shopping card state failed!", MediaType.TEXT_HTML).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.ok("Error: Something went wrong!", MediaType.TEXT_HTML).build();
    }
}
