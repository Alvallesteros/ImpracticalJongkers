package app.rest.controllers;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.ItemComponent;
import app.entities.*;

@Path("/item")
public class ItemController {

	@Autowired
    ItemComponent itemComponent;

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Item addItem(ItemDto itemDto)
    {
        return itemComponent.newItem(itemDto);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> viewItems(@FormParam("vendorId") Long vendorId)
    {
        return itemComponent.viewItems(vendorId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/toggleStock")
    public String toggleItemStock(@FormParam("itemId") Long itemId) {
        return itemComponent.toggleItemStock(itemId);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public Item editItem(@FormParam("itemId") Long itemId, 
    					 @FormParam("itemName") String itemName, 
    					 @FormParam("itemPrice") double itemPrice,
    					 @FormParam("itemDescription") String itemDescription)
    {
    	return itemComponent.editItem(itemId, itemName, itemPrice, itemDescription);
    }
}
