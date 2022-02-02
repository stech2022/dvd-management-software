package com.example.dvdmanagementsoftware;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class DVDManagementSoftwareResources {
    @GET
    @Produces("text/plain")
    public String test(){
        return "Hello user!";
    }
}
