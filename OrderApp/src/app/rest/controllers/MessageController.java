package app.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import javax.ws.rs.FormParam;

import app.components.*;

@Path("/message")
public class MessageController {

    @Autowired
    InterComponent interComponent;
	// TODO: see component
    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String send(@FormParam("subject")     String contactNo,
                       @FormParam("message") String message)
    {
        return interComponent.send(contactNo, message);
    }

}
