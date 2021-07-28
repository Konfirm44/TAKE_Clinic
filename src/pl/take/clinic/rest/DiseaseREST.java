package pl.take.clinic.rest;

import pl.take.clinic.ejb.DiseaseEJB;
import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Disease;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/diseases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DiseaseREST implements DiseaseRestModel {
    @EJB
    DiseaseEJB bean;

    @Override
    @GET
    @Path("/")
    public List<Disease> getAll() {
        return bean.getAll();
    }

    @Override
    @GET
    @Path("/{id}")
    public Disease getById(@PathParam("id") Integer id) {
        return bean.getById(id);
    }

    @Override
    @POST
    @Path("/")
    public Response create(@QueryParam("contagious") String contagious, @QueryParam("name") String name) {
        CreationStatus response = bean.create(contagious, name);

        switch (response) {
            case Success:
                String json = " { \"status\": \"" + response + "\" } ";
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            default:
                return Response.serverError().entity("Received status: " + response).build();
        }
    }

    @Override
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @QueryParam("contagious") String contagious, @QueryParam("name") String name) {
        CreationStatus response = bean.update(id, contagious, name);

        switch (response) {
            case Success:
                String json = " { \"status\": \"" + response + "\" } ";
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            default:
                return Response.serverError().entity("Received status: " + response).build();
        }
    }
}
