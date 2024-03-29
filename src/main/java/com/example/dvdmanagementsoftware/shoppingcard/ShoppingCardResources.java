package com.example.dvdmanagementsoftware.shoppingcard;

import com.example.dvdmanagementsoftware.database.Database;
import com.example.dvdmanagementsoftware.messages.ErrorMessage;
import com.example.dvdmanagementsoftware.messages.SuccessMessage;
import com.example.dvdmanagementsoftware.users.Role;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cards")
public class ShoppingCardResources {
    Database db = new Database();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingCards(@HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE, "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("User authentication failed!")).build();
        }
        List<ShoppingCard> sCards = db.getShoppingCards();
        System.out.println("Info: Found " + sCards.size() + " shopping card(s) on DB");
        return Response
                .status(Response.Status.OK)
                .entity(sCards)
                .build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingCard(@PathParam("id") int id, @HeaderParam("token") String givenToken) {
        ShoppingCard s = db.getShoppingCard(id);
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE + "/" + Role.CUSTOMER, ""+s.getUserId());
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("User authentication failed!")).build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(s)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteShoppingCard(@PathParam("id") int id, @HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE, "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("User authentication failed!")).build();
        }
        boolean status = db.deleteShoppingCard(id);
        if(status) return Response.status(Response.Status.OK).entity(new SuccessMessage("Shopping card deleted successfully!")).build();
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("Shopping card deletion failed!")).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newShoppingCard(ShoppingCard shoppingCard, @HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE, "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("User authentication failed!")).build();
        }
        try {
            boolean status = db.newShoppingCard(shoppingCard);
            if (status) return Response.status(Response.Status.OK).entity(new SuccessMessage("New Shopping card create successfully!")).build();
            return Response.status(Response.Status.OK).entity(new ErrorMessage("Shopping card creation failed!")).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("Error: Something went wrong!")).build();
    }

    @POST
    @Path("/{id}/state/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeState(@PathParam("id") int id, String state, @HeaderParam("token") String givenToken) {
        boolean isAuthenticated = db.authenticate(givenToken, Role.ADMIN + "/" + Role.EMPLOYEE, "");
        if (!isAuthenticated) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("User authentication failed!")).build();
        }
        try {
            JSONObject obj = new JSONObject(state);
            String sstate = obj.getString("state");
            boolean status = db.changeStateShoppingCard(id, sstate);
            if (status) return Response.status(Response.Status.OK).entity(new SuccessMessage("Shopping card state updated successfully!")).build();
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("Shopping card state did not change!")).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("Something went wrong!")).build();
    }
}
