package com.dab.household.api;

import com.dab.household.service.ItemService;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/item")
public class ItemApi {
    @EJB
    private ItemService itemService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllItemsJson() {
        return new Gson().toJson(itemService.findAll());
    }

    @GET
    @Path("search/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTitleItemsJson(@PathParam("title") String title) {
        return new Gson().toJson(itemService.findItemsByTitle(title));
    }
}
