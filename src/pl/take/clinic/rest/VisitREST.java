package pl.take.clinic.rest;

import pl.take.clinic.ejb.VisitsEJB;
import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Visit;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/visits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitREST implements VisitRestModel {
    @EJB
    VisitsEJB bean;

    @Override
    @GET
    @Path("/")
    public List<Visit> getAll() {
        return bean.getAll();
    }

    @Override
    @GET
    @Path("/{id}")
    public Visit getById(@PathParam("id") Long id) {
        return bean.getById(id);
    }

    @Override
    @POST
    @Path("/")
    public Response create(
            @QueryParam("note") String note,
            @QueryParam("status") Integer status,
            @QueryParam("doctorId") Long doctorId,
            @QueryParam("patientId") Long patientId,
            @QueryParam("timestamp") String timestamp
    ) {
        CreationStatus response = bean.create(note, status, doctorId, patientId, timestamp);

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
    public Response update(
            @PathParam("id") Long id,
            @QueryParam("note") String note,
            @QueryParam("status") Integer status,
            @QueryParam("doctorId") Long doctorId,
            @QueryParam("patientId") Long patientId,
            @QueryParam("timestamp") String timestamp
    ) {
        CreationStatus response = bean.update(id, note, status, doctorId, patientId, timestamp);

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
    @Path("/{id}/status")
    public Response updateStatus(
            @PathParam("id") Long id,
            @QueryParam("status") Integer status
    ) {
        CreationStatus response = bean.updateStatus(id, status);

        switch (response) {
            case Success:
                String json = " { \"status\": \"" + response + "\" } ";
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            default:
                return Response.serverError().entity("Received status: " + response).build();
        }
    }
}
